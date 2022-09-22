package com.silentium.controller;

import com.silentium.dto.ParamSearchTourDto;
import com.silentium.dto.TourDto;
import com.silentium.model.*;
import com.silentium.repository.*;
import com.silentium.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class SearchController {
    @Autowired
    private TourOperatorRepository tourOperatorRepository;
    @Autowired
    private TourDateRepository tourDateRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TypeTourRepository typeTourRepository;
    @Autowired
    private TypeFoodRepository typeFoodRepository;
    @Autowired
    private TypeTransportRepository typeTransportRepository;
    @Autowired
    private AccountService AccountService;
    @PersistenceContext
    private EntityManager em;

    @PostMapping("/guestsearch")
    public String searchPageGuestGET(@Param("search_text") String search_text, Model model) {
        ParamSearchTourDto paramSearchTourDtoSearch = new ParamSearchTourDto();
        paramSearchTourDtoSearch.setSearch_text(search_text);
        initModelSearch(paramSearchTourDtoSearch, model);
        initModelAuthentication(model);
        return "/search";
    }

    @GetMapping("/search")
    public String searchPageUserGET(Model model) {
        ParamSearchTourDto paramSearchTourDtoSearch = null;
        Object object = model.getAttribute("paramSearchTourDtoSearch");
        if (object == null) {
            paramSearchTourDtoSearch = new ParamSearchTourDto();
        } else {
            paramSearchTourDtoSearch = (ParamSearchTourDto) object;
        }
        initModelSearch(paramSearchTourDtoSearch, model);
        initModelAuthentication(model);
        return "/search";
    }

    @PostMapping("/search")
    public String searchPageUserPOST(@ModelAttribute("toursSearch") ParamSearchTourDto paramSearchTourDtoSearch, BindingResult res, Model model) throws ParseException {
        initModelSearch(paramSearchTourDtoSearch, model);
        initModelAuthentication(model);
        return "/search";
    }

    @PostMapping("/search/selectTour.do")
    public ModelAndView selectTourAdminPOST(@ModelAttribute("tourDate_id") int tourDate_id, Model model) throws ParseException {
        String url = "redirect:/tourpage?id=" + tourDate_id;
        return new ModelAndView(url);
    }

    public void initModelSearch(ParamSearchTourDto paramSearchTourDtoSearch, Model model) {
        int quantity_day = paramSearchTourDtoSearch.getQuantity_day();
        int typetour_id = paramSearchTourDtoSearch.getTypetour_id();
        int typefood_id = paramSearchTourDtoSearch.getTypefood_id();
        int typetransport_id = paramSearchTourDtoSearch.getTypetransport_id();
        int touroperator_id = paramSearchTourDtoSearch.getTouroperator_id();
        int price_min = paramSearchTourDtoSearch.getPrice_min();
        int price_max = paramSearchTourDtoSearch.getPrice_max();
        String search_text = paramSearchTourDtoSearch.getSearch_text();
        String search_text_cont = "'" + search_text + "*'";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date_travel_param = paramSearchTourDtoSearch.getDate_travel();
        Date date_travel = new Date();
        if (!date_travel_param.isEmpty()) {
            try {
                date_travel = format.parse(date_travel_param);
            } catch (ParseException e) {
                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        List<TourDate> tourDates = tourDateRepository.findAllSearchByParameter(quantity_day,
                typetour_id, typefood_id, typetransport_id, touroperator_id,
                price_min, price_max, date_travel, search_text, search_text_cont);
        model.addAttribute("toursSearch", tourDates);
        model.addAttribute("paramSearchTourDto", paramSearchTourDtoSearch);
        model.addAttribute("typetours", typeTourRepository.findAll());
        model.addAttribute("typefoods", typeFoodRepository.findAll());
        model.addAttribute("typetransports", typeTransportRepository.findAll());
        model.addAttribute("touroperators", tourOperatorRepository.findAll());
    }

    public void initModelAuthentication(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account user = AccountService.findUserByLogin(auth.getName());
        if (user == null) {
            model.addAttribute("userName", "Гость");
            model.addAttribute("userSurName", "");
            model.addAttribute("userEmail", "");
            model.addAttribute("role", "NONE");
        } else {
            model.addAttribute("userName", user.getPerson().getName());
            model.addAttribute("userSurName", user.getPerson().getSurname());
            model.addAttribute("userEmail", user.getLogin());
            model.addAttribute("role", user.getRole());
        }
    }
}


