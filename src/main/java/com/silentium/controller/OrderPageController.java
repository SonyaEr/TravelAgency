package com.silentium.controller;

import com.silentium.model.*;
import com.silentium.repository.*;
import com.silentium.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.silentium.controller.LoginController.*;

import java.util.Date;
import java.util.List;


@Controller
public class OrderPageController {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TourRepository tourRepository;
	@Autowired
	private TourDateRepository tourDateRepository;
	@Autowired
	private TourStructureRepository tourStructureRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private StatusOrderRepository statusOrderRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/user/orderpage")
	public String tourpageUser(@RequestParam (name = "id") int select_id, Model model) {
		initModelOrderPage(select_id, model);
		return "/user/orderpage";
	}

	@GetMapping("/admin/orderpage")
	public String tourpageAdmin(@RequestParam (name = "id") int select_id, Model model) {
		initModelOrderPage(select_id, model);
		return "/admin/orderpage";
	}

	@PostMapping({"/user/orderpage/createOrder.do", "/admin/orderpage/createOrder.do"})
	public ModelAndView createOrderUser(@RequestParam (name = "tourDate_id") int select_id,
										@RequestParam (name = "tour_date_count") int count,
										Model model){
		createOrder(select_id, count);
		return new ModelAndView(selectUrl("redirect:/user/home","redirect:/admin/home"));
	}

	@PostMapping("/user/orderpage/updateOrder.do")
	public ModelAndView updateOrderUser(@RequestParam(name = "order_id") int select_id,
										@RequestParam(name = "tour_date_count") int select_count,
										@RequestParam(name = "comment_order") String select_comment,
										Model model) {
		updateOrderUser(select_id, select_count, select_comment);
		return new ModelAndView("redirect:/user/home");
	}

	@PostMapping("/admin/orderpage/updateOrder.do")
	public ModelAndView updateOrderAdmin(@RequestParam (name = "order_id") int select_id,
										 @RequestParam (name = "status_order") int select_statusorder,
										 @RequestParam (name = "tour_date_count") int select_count,
										 @RequestParam (name = "note_order") String select_note,
										 Model model){
		updateOrderAdmin(select_id, select_count, select_note, select_statusorder);
		return new ModelAndView("redirect:/admin/home");
	}

	private void initModelOrderPage(int select_id, Model model) {
		List<TourStructure> tourStructures= tourStructureRepository.findByTourId(select_id);
		model.addAttribute("tourStructures", tourStructures);
		model.addAttribute("tour", tourRepository.findById(select_id));
		model.addAttribute("tourDates", tourDateRepository.findByTourId(select_id));
		model.addAttribute("order", orderRepository.findById(select_id));
		model.addAttribute("statusorders", statusOrderRepository.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());
		model.addAttribute("userName", user.getPerson().getName()) ;
		model.addAttribute("userSurName", user.getPerson().getSurname());
		model.addAttribute("userEmail", user.getLogin());
	}

	private void updateOrderUser(int order_id, int count, String comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());
		String role = accountRepository.findRoleByLogin(auth.getName());
		if (!role.equals("USER")) {
			return;
		}
		Order order = orderRepository.findById(order_id);
		if (order == null) {
			return;
		}
		if (order.getClient().getId() != user.getPerson().getId()) {
			return;
		}
		if (order.getStatusOrder().getId() != 1) {
			return;
		}
		updateOrder(order, user, role, count, comment, null, 0);
	}

	private void updateOrderAdmin(int order_id, int count, String note, int status_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());
		String role = accountRepository.findRoleByLogin(auth.getName());
		if (!role.equals("ADMIN")) {
			return;
		}
		Order order = orderRepository.findById(order_id);
		if (order == null) {
			return;
		}
		if (order.getManager() != null) {
			if (order.getManager().getId() != user.getPerson().getId()) {
				return;
			}
		}
		updateOrder(order, user, role, count, null, note, status_id);
	}

	private void updateOrder(Order order, Account user, String role, int count, String comment, String note, int status_id) {
		if (role.equals("ADMIN")) {
			if (order.getStatusOrder().getId() != status_id) {
				StatusOrder statusOrder = statusOrderRepository.findById(status_id);
				order.setStatusOrder(statusOrder);
			}
			if (order.getNote() != note) {
				order.setNote(note);
			}
			if (order.getCount() != count) {
				order.setCount(count);
			}
			if (order.getManager() == null) {
				order.setManager(user.getPerson());
			}
		}
		if (role.equals("USER")) {
			if (order.getComment() != comment) {
				order.setComment(comment);
			}
			if (order.getCount() != count) {
				order.setCount(count);
			}
		}
		orderRepository.saveAndFlush(order);
	}

	private void createOrder(int select_id, int count){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());
		Person client = personRepository.findByAccountId(user.getId());
		TourDate tourDate = tourDateRepository.findById(select_id);
		StatusOrder statusOrder = statusOrderRepository.findById(1);
		Order order = orderRepository.findById(select_id);
		order.setClient(client);
		order.setTourDate(tourDate);
		order.setStatusOrder(statusOrder);
		order.setOrderDate(new Date());
		order.setCount(count);
		orderRepository.save(order);
	}

	public String selectUrl(String urlUser, String urlAdmin){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = accountRepository.findRoleByLogin(auth.getName());
		if (role.equals("ADMIN")) {
			return urlAdmin;
		}
		return urlUser;
	}
}


