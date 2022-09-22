package com.silentium.controller;

import com.silentium.dto.*;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import com.silentium.model.*;
import com.silentium.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.silentium.service.AccountService;

import java.text.ParseException;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

@Controller
public class LoginController {
//
	//
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private AccountService AccountService;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@RequestMapping("/")
	public String start(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = AccountService.findUserByLogin(auth.getName());
		if (user == null) {
			model.addAttribute("role", "NONE");
		} else {
			model.addAttribute("role", user.getRole());
		}
		List<Country> countryList = countryRepository.findAllLimit();
		model.addAttribute("countres", countryList);
		return "index";
	}

	@GetMapping("/login")
	public ModelAndView login(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = AccountService.findUserByLogin(auth.getName());
		if (user == null) {
			model.addAttribute("role", "NONE");
		} else {
			model.addAttribute("role", user.getRole());
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping("/login/logined")
	public ModelAndView logined() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = accountRepository.findRoleByLogin(auth.getName());
		if (role.equals("ADMIN")) {
			return new ModelAndView("redirect:/admin/home");
		}
		return new ModelAndView("redirect:/user/home");
	}

	@GetMapping("/home")
	public ModelAndView home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = accountRepository.findRoleByLogin(auth.getName());
		if (role.equals("ADMIN")) {
			return new ModelAndView("redirect:/admin/home");
		}
		return new ModelAndView("redirect:/user/home");
	}

	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		UserDto user = new UserDto();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@PostMapping("/registration")
	public ModelAndView createNewUser(@Valid UserDto userDto, BindingResult bindingResult)  throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user", userDto);
			bindingResult.rejectValue("name", "error.user",
					"There is already a user registered with the email provided");		modelAndView.setViewName("registration");
			return  modelAndView;
		}
		userDto.setLogin(toLowerCase(userDto.getLogin()));
		Account account = convertToEntityUser(userDto);

		Account userExists = AccountService.findUserByLogin(account.getLogin());
		if (userExists != null) {
			modelAndView.addObject("user", userDto);
			bindingResult.rejectValue("login", "error.user",
					"There is already a user registered with the email provided");
			return  modelAndView;
		}

		Person person = new Person(userDto.getName(), userDto.getSurname(), userDto.getLogin());
		person.setRating((double) 0);
		personRepository.save(person);
		account.setPerson(person);

		AccountService.saveAccount(account);
		modelAndView.addObject("successMessage", "User has been registered successfully");
		modelAndView.addObject("user", new Account());
		modelAndView.setViewName("login");
		return modelAndView;
	}

	private UserDto convertEntityUserToDto(Account user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	private Account convertToEntityUser(UserDto userDto) throws ParseException {
		Account user = modelMapper.map(userDto, Account.class);
		Person client_id = personRepository.findById(userDto.getPerson_id());
		//String email = clientRepository.findByEmail(userDto.getPerson_id());
		//user.setLogin(email);
		user.setPerson(client_id);
		return user;
	}

	public void initModelAuthentication(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = AccountService.findUserByLogin(auth.getName());
		if (user == null) {
			model.addAttribute("role", "NONE");
		} else {
			model.addAttribute("role", user.getRole());
		}
	}

}

