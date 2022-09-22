package com.silentium.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silentium.model.Order;
import com.silentium.model.StatusOrder;
import com.silentium.model.Tour;
import com.silentium.model.TourOperator;
import com.silentium.repository.OrderRepository;
import com.silentium.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("apiService")
public class ApiService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TourRepository tourRepository;
    public ApiService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

   public String getStatusOrder(int id){
       return orderRepository.findById(id).getStatusOrder().getName();
   }
    public Order getOrder(int id){
        return orderRepository.findById(id);
    }
    public Tour getTour(int id){
        return tourRepository.findById(id);
    }
}
