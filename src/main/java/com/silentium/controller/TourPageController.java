package com.silentium.controller;

import com.silentium.dto.ParamEditTourDto;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TourPageController {
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
    private AccountService AccountService;
    @Autowired
    private TourOperatorRepository tourOperatorRepository;
    @Autowired
    private TypeTourRepository typeTourRepository;
    @Autowired
    private TypeFoodRepository typeFoodRepository;
    @Autowired
    private TypeTransportRepository typeTransportRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/tourpage")
    public String tourpageUser(@RequestParam(name = "id") int select_id, Model model) {
        initModelTourPage(select_id, model);
        initModelAuthentication(model);
        return "/tourpage";
    }

    @PostMapping("/tourpage/createOrder.do")
    public ModelAndView createOrderUser(@RequestParam(name = "tourDate_id") int select_id,
                                        @RequestParam(name = "tour_date_count") int tour_date_count,
                                        Model model) {
        createOrder(select_id, tour_date_count);
        return new ModelAndView(selectUrl("redirect:/user/home","redirect:/admin/home"));
    }

    @GetMapping("/admin/edittour")
    public String edittourpageAdmin(@RequestParam(name = "id") int select_id, Model model) throws ParseException {
        ParamEditTourDto paramEditTourDto = new ParamEditTourDto();
        initModelEditTour(paramEditTourDto, model, select_id);
        initModelAuthentication(model);
        return "/admin/edittour";
    }

    @PostMapping("/admin/edittour/savetour.do")
    public ModelAndView updateTour(@ModelAttribute("paramEditTourDto") ParamEditTourDto paramEditTourDto, BindingResult res, Model model) throws ParseException {
        if (res.hasErrors()) {
            model.addAttribute("clientSave", new Person());
            model.addAttribute("hotelSave", new Hotel());
            return new ModelAndView("redirect:/admin/home");
        }
        Tour tour = convertToEntityTour(paramEditTourDto);
        tourRepository.save(tour);
        return new ModelAndView("redirect:/admin/home");
    }

    @PostMapping("/admin/edittour/addTourDate.do")
    public ModelAndView createOrderUser(@RequestParam(name = "new_dateArrival") String new_dateArrival,
                                        @RequestParam(name = "new_price") float new_price,
                                        @RequestParam(name = "tour_id") int tour_id,
                                        Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateArrival = new Date();
        if (!new_dateArrival.isEmpty()) {
            try {
                dateArrival = format.parse(new_dateArrival);
            } catch (ParseException e) {
            }
        }
        TourDate tourDate = new TourDate();
        tourDate.setTour(tourRepository.getById(tour_id));
        tourDate.setDateArrival(dateArrival);
        tourDate.setPrice(new_price);
        tourDateRepository.save(tourDate);

        String url = "redirect:/admin/edittour?id=" + tour_id;
        return new ModelAndView(url);
    }

    private void initModelEditTour(ParamEditTourDto paramEditTourDto, Model model, int select_id) throws ParseException {
        Tour tour = null;
        if (select_id != 0) {
            tour=tourRepository.findById(select_id);
            if (tour != null) {
                paramEditTourDto.setId(tour.getId());
                paramEditTourDto.setDescription(tour.getDescription());
                paramEditTourDto.setName(tour.getName());
                paramEditTourDto.setQuantity_night(tour.getQuantityNight());
                paramEditTourDto.setTouroperator_id(tour.getTourOperator().getId());
                paramEditTourDto.setTypefood_id(tour.getTypeFood().getId());
                paramEditTourDto.setTypetour_id(tour.getTypeTour().getId());
                paramEditTourDto.setTypetransport_id(tour.getTypeTransport().getId());
            }
        }
        model.addAttribute("paramEditTourDto", paramEditTourDto);
        model.addAttribute("tourDates", tourDateRepository.findByTourIdAll(paramEditTourDto.getId()));
        model.addAttribute("tourStructures",tourStructureRepository.findByTourId(paramEditTourDto.getId()));
        model.addAttribute("typetours", typeTourRepository.findAll());
        model.addAttribute("typefoods", typeFoodRepository.findAll());
        model.addAttribute("typetransports", typeTransportRepository.findAll());
        model.addAttribute("touroperators", tourOperatorRepository.findAll());
    }

    private void initModelTourPage(int select_id, Model model) {
        List<TourStructure> tourStructures = tourStructureRepository.findByTourId(select_id);
        model.addAttribute("tourStructures", tourStructures);
        model.addAttribute("tour", tourRepository.findById(select_id));
        model.addAttribute("tourDates", tourDateRepository.findByTourId(select_id));
    }

    private void initModelAuthentication(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account user = AccountService.findUserByLogin(auth.getName());
        if (user == null) {
            model.addAttribute("userName", "Гость");
            model.addAttribute("userSurName", "-");
            model.addAttribute("userEmail", "-");
            model.addAttribute("role", "NONE");
        } else {
            model.addAttribute("userName", user.getPerson().getName());
            model.addAttribute("userSurName", user.getPerson().getSurname());
            model.addAttribute("userEmail", user.getLogin());
            model.addAttribute("role", user.getRole());
        }
    }

    private void createOrder(int select_id, int tour_date_count) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account user = AccountService.findUserByLogin(auth.getName());
        Person client = personRepository.findByAccountId(user.getId());
        TourDate tourDate = tourDateRepository.findById(select_id);
        StatusOrder statusOrder = statusOrderRepository.findById(1);
        Order order = new Order();
        order.setClient(client);
        order.setTourDate(tourDate);
        order.setStatusOrder(statusOrder);
        order.setOrderDate(new Date());
        order.setCount(tour_date_count);
        orderRepository.save(order);
    }

    private String selectUrl(String urlUser, String urlAdmin){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = accountRepository.findRoleByLogin(auth.getName());
        if (role.equals("ADMIN")) {
           return urlAdmin;
        }
        return urlUser;
    }

    private Tour convertToEntityTour(ParamEditTourDto paramEditTourDto) throws ParseException {
        Tour tour = modelMapper.map(paramEditTourDto, Tour.class);
        tour.setTypeTour(typeTourRepository.findById(paramEditTourDto.getTypetour_id()));
        tour.setTypeFood(typeFoodRepository.findById(paramEditTourDto.getTypefood_id()));
        tour.setTypeTransport(typeTransportRepository.findById(paramEditTourDto.getTypetransport_id()));
        tour.setTourOperator(tourOperatorRepository.findById(paramEditTourDto.getTouroperator_id()));
        tour.setQuantityNight(paramEditTourDto.getQuantity_night());
        return tour;
    }
}

