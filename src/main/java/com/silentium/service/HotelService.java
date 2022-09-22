package com.silentium.service;

import com.silentium.model.Hotel;
import com.silentium.repository.CityRepository;
import com.silentium.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<Hotel> getHotelList(int page, int size, String sortDir, String sort) {

        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<Hotel> hotels = hotelRepository.findAll(pageReq);
        return hotels.getContent();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public Hotel createHotel(Hotel post) {
        return hotelRepository.save(post);
    }

    @Override
    public Hotel getHotelById(long id) {
        return hotelRepository.getOne(id);
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        Hotel savedHotel= hotelRepository.saveAndFlush(hotel);
        return savedHotel;
    }

    @Override
    public void delete(long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel getByName(String name) {
        return hotelRepository.findByName(name);
    }

    @Override
    public Hotel editHotel(Hotel bank) {
        return hotelRepository.saveAndFlush(bank);
    }

}
