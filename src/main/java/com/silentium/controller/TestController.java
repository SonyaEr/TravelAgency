package com.silentium.controller;

import com.silentium.dto.HotelDto;
import com.silentium.dto.ParamSearchTourDto;
import com.silentium.dto.UserDto;
import com.silentium.model.*;
import com.silentium.repository.*;
import com.silentium.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private PersonRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private TypeTourRepository typetourRepository;
    @Autowired
    private TourOperatorRepository tourOperatorRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = {"/login/test"}, method = RequestMethod.GET)
    public String test(Model model) {

        String tq = "SELECT \n" +
                " t.tour_id,\n" +
                " t.name,\n" +
                " count(v.voucher_id) AS totalVoucher\n" +
                "FROM voucher v\n" +
                "LEFT JOIN tour t\n" +
                "ON (v.fk_tour_date_id =  t.tour_id)\n" +
                "GROUP BY \n" +
                " tour_id\n" +
                "ORDER BY totalVoucher DESC\n";

        Query qu = em.createNativeQuery(tq);
        List<String> resultlist = qu.getResultList();
        model.addAttribute("results", resultlist);
        return "/test";
    }
}

