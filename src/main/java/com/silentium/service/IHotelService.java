package com.silentium.service;

import com.silentium.model.Hotel;

import java.util.List;

public interface IHotelService {
    List<Hotel> getHotelList(int page, int size, String sortDir, String sort);

    void updateHotel(Hotel hotel);
    Hotel createHotel(Hotel hotel);
    Hotel getHotelById(long id);

    Hotel addHotel(Hotel hotel);
    void delete(long id);
    Hotel getByName(String name);
    Hotel editHotel(Hotel hotel);
}

