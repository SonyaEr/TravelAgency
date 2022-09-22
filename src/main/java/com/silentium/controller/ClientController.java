package com.silentium.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import com.silentium.dto.HotelDto;
import com.silentium.dto.OrderDto;
import com.silentium.model.*;
import com.silentium.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.silentium.service.AccountService;


import java.text.ParseException;
import java.util.Date;

@Controller
public class ClientController {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TourOperatorRepository tourOperatorRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TourRepository tourRepository;
	@Autowired
	private TourDateRepository tourDateRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
	private StatusVoucherRepository statusVoucherRepository;
	@Autowired
	private StatusOrderRepository statusOrderRepository;
	@Autowired
	private AccountService AccountService;

	@GetMapping("/user/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = AccountService.findUserByLogin(auth.getName());
		int client_id = user.getPerson().getId();
		modelAndView.addObject("userName", user.getPerson().getName()) ;
		modelAndView.addObject("userSurName", user.getPerson().getSurname());
		modelAndView.addObject("userEmail", user.getLogin());

		modelAndView.addObject("person", personRepository.findById(client_id));
		modelAndView.addObject("orders", orderRepository.findByClientId(client_id));
		modelAndView.addObject("vouchers", voucherRepository.findByClientId(client_id));
		modelAndView.addObject("clientnew", new Person());

		modelAndView.setViewName("/user/home");
		return modelAndView;
	}

	@PostMapping("/user/home/editOrder.do")
	public ModelAndView editOrderUser(@RequestParam (name = "order_id") int select_id, Model model){
		String url = "redirect:/user/orderpage?id=" + select_id;
		return new ModelAndView(url);
	}

	@PostMapping("/user/saveClient.do")
	public String saveClient(@Valid @ModelAttribute("clientSave") Person clientSave, BindingResult res, Model model) {
		if (!res.hasErrors()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Account user = AccountService.findUserByLogin(auth.getName());
			int client_id = user.getPerson().getId();
			if (client_id==clientSave.getId()) {
				Person person_bd = personRepository.findById(client_id);
				clientSave.setEmail(person_bd.getEmail());
				clientSave.setRating(person_bd.getRating());
				personRepository.save(clientSave);
			}
			return "redirect:/user/home";
		}

		if (clientSave.getRating()==null){
			clientSave.setRating(4.4);
		}

		return "redirect:/user/home";
	}

	@PostMapping("/user/home/editVoucher.do")
	public ModelAndView editVoucherAdmin(@RequestParam (name = "voucher_id") int select_id, Model model){
		String url = "redirect:/user/voucherpage?id=" + select_id;
		return new ModelAndView(url);
	}

	private HotelDto convertEntityHotelToDto(Hotel hotel) {
		HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
		return hotelDto;
	}

	private Hotel convertToEntityHotel(HotelDto hotelDto) throws ParseException {
		Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
		City city_id = cityRepository.findById(hotelDto.getCity_id());
		hotel.setCity(city_id);
		return hotel;
	}

	private OrderDto convertEntityOrderToDto(Order order) {
		OrderDto orderDto = modelMapper.map(order, OrderDto.class);
		return orderDto;
	}

	private Order convertToEntityOrder(OrderDto orderDto) throws ParseException {
		Order order = modelMapper.map(orderDto, Order.class);
		TourDate tour_id = tourDateRepository.findById(orderDto.getTour_id());
		order.setTourDate(tour_id);
		return order;
	}}

