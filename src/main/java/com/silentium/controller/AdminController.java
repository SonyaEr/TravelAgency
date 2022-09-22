package com.silentium.controller;

import com.silentium.dto.HotelDto;
import com.silentium.dto.OrderDto;
import com.silentium.model.*;
import com.silentium.repository.*;
import com.silentium.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AdminController {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TourOperatorRepository tourOperatorRepository;
	@Autowired
	private TourRepository tourRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TourDateRepository tourDateRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
	private StatusVoucherRepository statusVoucherRepository;
	@Autowired
	private StatusOrderRepository statusOrderRepository;
	@Autowired
	private TypeTourRepository typetourRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TypePaymentRepository typePaymentRepository;
	@Autowired
	private VoucherClientRepository voucherClientRepository;

	@PersistenceContext
	private EntityManager em;

	//GET methods

	@GetMapping("/admin/home")
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());
		modelAndView.addObject("userName", user.getPerson().getName());
		modelAndView.addObject("userSurName", user.getPerson().getSurname());
		modelAndView.addObject("userEmail", user.getLogin());
		modelAndView.addObject("role", user.getRole());
		modelAndView.setViewName("/admin/home");

		modelAndView.addObject("freeorders", orderRepository.findByWithoutManager());
		modelAndView.addObject("orders", orderRepository.findByManagerId(user.getPerson().getId()));
		modelAndView.addObject("vouchers", voucherRepository.findByManagerId(user.getPerson().getId()));
		modelAndView.addObject("clients", personRepository.findAll());
		modelAndView.addObject("tours", tourRepository.findAll(1,5));

		modelAndView.addObject("citys", cityRepository.findAll());
		modelAndView.addObject("countrys", countryRepository.findAll());
		modelAndView.addObject("hotels", hotelRepository.findAll());
		modelAndView.addObject("typetours", typetourRepository.findAll());
		modelAndView.addObject("tourOperators", tourOperatorRepository.findAll());

		modelAndView.addObject("citySave", new City());
		modelAndView.addObject("countrySave", new Country());
		modelAndView.addObject("clientSave", new Person());
		modelAndView.addObject("typetourSave", new TypeTour());
		modelAndView.addObject("tourOperatorSave", new TourOperator());
		modelAndView.addObject("hotelSave", new HotelDto());
		//modelAndView.addObject("paramSearchTourDto", new ParamSearchTourDto());

		return modelAndView;
	}

	@GetMapping("/admin/selectperson")
	public ModelAndView adminSelectPerson() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());

		modelAndView.setViewName("/admin/selectperson");

		modelAndView.addObject("clients", personRepository.findAll());

		//modelAndView.addObject("paramSearchTourDto", new ParamSearchTourDto());

		return modelAndView;
	}


	//POST methods

	@PostMapping("/admin/saveClient.do")
	public String saveClientAdmin(@Valid @ModelAttribute("clientSave") Person clientSave, BindingResult res, Model model) {
		if (res.hasErrors()) {
			model.addAttribute("orderSave", new Order());
			model.addAttribute("hotelSave", new Hotel());
			return "admin/home";
		}
		personRepository.save(clientSave);
		return "redirect:/admin/home";
	}

	@PostMapping("/admin/saveHotel.do")
	public ModelAndView saveHotelAdmin(@ModelAttribute("hotelSave") HotelDto hotelDtoSave, BindingResult res, Model model) throws ParseException {

		Hotel hotel = convertToEntityHotel(hotelDtoSave);
		hotelRepository.save(hotel);
		return new ModelAndView("redirect:/admin/home");

	}

	@PostMapping("/admin/deleteHotel.do")
	public String delete(Hotel hotel) {
		hotelRepository.delete(hotel);
		return "redirect:/admin/home";
	}

	@PostMapping("/admin/saveOrder.do")
	public ModelAndView saveOrderAdmin(@Valid @ModelAttribute("orderSave") OrderDto orderDtoSave, BindingResult res, Model model) throws ParseException{
		if (res.hasErrors()) {
			model.addAttribute("clientSave", new Person());
			model.addAttribute("hotelSave", new Hotel());
			return new ModelAndView("redirect:/admin/home");
		}
		Order order = convertToEntityOrder(orderDtoSave);
		orderRepository.save(order);
		return new ModelAndView("redirect:/admin/home");

	}

	@PostMapping("/admin/home/cancelOrder.do")
	public ModelAndView cancelOrderAdmin(@RequestParam (name = "order_id") int select_id, Model model){
		Order order = orderRepository.findById(select_id);
		StatusOrder statusOrder = statusOrderRepository.findById(3);
		order.setStatusOrder(statusOrder);
		orderRepository.save(order);
		return new ModelAndView("redirect:/admin/home");
	}

	@PostMapping("/admin/home/createVoucher.do")
	public ModelAndView createVoucherAdmin(@RequestParam (name = "order_id") int select_id, Model model){
		Voucher voucherBD = voucherRepository.findByOrderId(select_id);
		if (voucherBD!=null){
			String url = "redirect:/admin/voucherpage?id=" + voucherBD.getId();
			return new ModelAndView(url);
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = accountService.findUserByLogin(auth.getName());
		Person manager = user.getPerson();
		Order order = orderRepository.findById(select_id);
		Person client = order.getClient();
		TourDate tourDate = order.getTourDate();
		StatusVoucher statusVoucher = statusVoucherRepository.findById(1);
		TypePayment payment = typePaymentRepository.findById(1);

		Voucher voucher = new Voucher();
		voucher.setAmount(tourDate.getPrice()*order.getCount());
		voucher.setCount(order.getCount());
		Date cur_date = new Date();
		voucher.setVoucherDate(cur_date);
		LocalDateTime voucer_date = new java.sql.Timestamp(cur_date.getTime()).toLocalDateTime();
		voucher.setVoucherUpdateDate(voucer_date);
		voucher.setPlace_iIssue("г.Харьков, Бородинивська ул., 9А");
		voucher.setOrder(order);
		voucher.setManager(manager);
		voucher.setTourDate(tourDate);
		voucher.setStatusVoucher(statusVoucher);
		voucher.setPayment(payment);
		HashSet<Person> persons = new HashSet<Person>();
		persons.add(client);
		voucher.setPersons(persons);
		try {
			voucherRepository.save(voucher);
		} catch (Exception e) {
			Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
			return new ModelAndView("redirect:/admin/home");
		}
		if (voucher.getId() != 0) {
			StatusOrder statusOrder = statusOrderRepository.findById(5);
			order.setStatusOrder(statusOrder);
			try {
				orderRepository.save(order);
			} catch (Exception e) {
				Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
				return new ModelAndView("redirect:/admin/home");
			}
//			VoucherClient voucherClient = new VoucherClient();
//			voucherClient.setVoucher(voucher);
//			voucherClient.setPerson(client);
//			//voucherClient.setId(1l);
//			try {
//				voucherClientRepository.save(voucherClient);
//			} catch (Exception e) {
//				Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
//				return new ModelAndView("redirect:/admin/home");
//			}
		}
		String url = "redirect:/admin/voucherpage?id=" + voucher.getId();
		return new ModelAndView(url);
	}

	@PostMapping("/admin/home/editOrder.do")
	public ModelAndView editOrderAdmin(@RequestParam (name = "order_id") int select_id, Model model){
		String url = "redirect:/admin/orderpage?id=" + select_id;
		return new ModelAndView(url);
	}

	@PostMapping("/admin/home/editVoucher.do")
	public ModelAndView editVoucherAdmin(@RequestParam (name = "voucher_id") int select_id, Model model){
		String url = "redirect:/admin/voucherpage?id=" + select_id;
		return new ModelAndView(url);
	}

	@PostMapping( "/admin/home/addTour")
	public ModelAndView addTourAdmin() {
		String url = "redirect:/admin/edittour?id=0";
		return new ModelAndView(url);
	}

	@PostMapping( "/admin/home/editTour")
	public ModelAndView editTourAdmin(@RequestParam(name = "tour_id") int select_id, Model model) {
		String url = "redirect:/admin/edittour?id=" + select_id;
		return new ModelAndView(url);
	}

	@PostMapping( "/admin/home/deleteTour")
	public ModelAndView deleteTourAdmin(@RequestParam(name = "tour_id") int select_id, Model model) {
		Tour tour = tourRepository.findById(select_id);
		if (tour != null) {
			tourRepository.delete(tour);
		}
		String url = "redirect:/admin/home";
		return new ModelAndView(url);
	}

	@PostMapping( "/admin/home/approveVoucher.do")
	public ModelAndView approveVoucherAdmin(@RequestParam (name = "voucher_id") int select_id, Model model){
		Voucher voucher = voucherRepository.findById(select_id);
		StatusVoucher statusVoucher = statusVoucherRepository.findById(2);
//		voucher.setStatus_voucher(statusVoucher);
		voucherRepository.save(voucher);
		return new ModelAndView("redirect:/admin/home");
	}

	@PostMapping( "/admin/home/cancelVoucher.do")
	public ModelAndView cancelVoucherAdmin(@RequestParam (name = "voucher_id") int select_id, Model model){
		Voucher voucher = voucherRepository.findById(select_id);
		StatusVoucher statusVoucher = statusVoucherRepository.findById(3);
//		voucher.setStatus_voucher(statusVoucher);
		voucherRepository.save(voucher);
		return new ModelAndView("redirect:/admin/home");
	}

	@PostMapping("/admin/saveTourOperator.do")
	public ModelAndView saveTourOperatorAdmin(@ModelAttribute("tourOperatorSave") TourOperator tourOperatorSave, BindingResult res, Model model){
		tourOperatorRepository.save(tourOperatorSave);
		return new ModelAndView("redirect:/admin/home");
	}

	@PostMapping("/admin/addHotel.do")
	public String addHotel(Hotel hotel) {
		return "redirect:/admin/addhotel";
	}

	@PostMapping("/admin/editHotel.do")
	public String editHotel(Hotel hotel) {
		return "redirect:/admin/addhotel";
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

