package com.silentium.controller;

import com.silentium.dto.HotelDto;
import com.silentium.model.Hotel;
import com.silentium.service.IHotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/addHotel")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<HotelDto> getHotels(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortDir") String sortDir,
            @RequestParam("sort") String sort) {


        List<Hotel> hotels = hotelService.getHotelList(page, size, sortDir, sort);
        return hotels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public HotelDto createHotel(@RequestBody HotelDto hotelDto) throws ParseException {
        Hotel hotel = convertToEntity(hotelDto);
        Hotel hotelCreated = hotelService.createHotel(hotel);
        return convertToDto(hotelCreated);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public HotelDto getHotel(@PathVariable("id") Long id) {
        return convertToDto(hotelService.getHotelById(id));
    }

    @RequestMapping("/admin/saveHotel")
    //@PutMapping(value = "/admin/saveHotel")
    @ResponseStatus(HttpStatus.OK)
    public void updateHotel(@RequestBody HotelDto hotelDto) throws ParseException {
        Hotel hotel = convertToEntity(hotelDto);
        hotelService.updateHotel(hotel);
    }


    private HotelDto convertToDto(Hotel hotel) {
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
   //     hotelDto.setSubmissionDate(hotel.getSubmissionDate(),AccountService.getCurrentUser().getPreference().getTimezone());
        return hotelDto;
    }

    private Hotel convertToEntity(HotelDto hotelDto) throws ParseException {
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        //hotel.setSubmissionDate(hotelDto.getSubmissionDateConverted(AccountService.getCurrentUser().getPreference().getTimezone()));

        if (hotelDto.getId() != 0) {
            Hotel oldHotel = hotelService.getHotelById(hotelDto.getId());
            //hotel.setRedditID(oldHotel.getRedditID());
            //hotel.setSent(oldHotel.isSent());
        }
        return hotel;
    }

}
