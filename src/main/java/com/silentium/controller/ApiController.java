package com.silentium.controller;

import com.silentium.model.Order;
import com.silentium.model.Person;
import com.silentium.model.Tour;
import com.silentium.repository.AccountRepository;
import com.silentium.repository.TourRepository;
import com.silentium.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("getStatusOrder/{id}")
    public ResponseEntity<String> getStatusOrder(@PathVariable("id") int id) {
        String statusOrder = apiService.getStatusOrder(id);
        return new ResponseEntity<String>(statusOrder, HttpStatus.OK);
    }

    @GetMapping("getOrder/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        Order order = apiService.getOrder(id);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @GetMapping("getTour/{id}")
    public ResponseEntity<Tour> getTour(@PathVariable("id") int id) {
        Tour tour = apiService.getTour(id);
        return new ResponseEntity<Tour>(tour, HttpStatus.OK);
    }
    @RequestMapping(value = "/getTours")
    public List<Tour> getTours() {
        return tourRepository.findAll();
    }
 //   @RequestMapping(value = "person/{login}")
  // public Person findByLogin(@PathVariable("login") String login) {
   //     return accountRepository.findPersonByLogin(login);
  //  }
}
