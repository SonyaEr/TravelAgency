package com.silentium.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParamSearchTourDto {

    private int country_id;
    private int city_id;
    private int typetour_id;
    private int touroperator_id;
    private int typefood_id;
    private int typetransport_id;
    private String date_travel;
    private int quantity_day;
    private int price_min;
    private int price_max;
    private String search_text;

    public ParamSearchTourDto() {
        this.country_id = -1;
        this.city_id = -1;
        this.typetour_id = -1;
        this.touroperator_id = -1;
        this.typefood_id = -1;
        this.typetransport_id = -1;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.date_travel = simpleDateFormat.format(new Date());
        this.quantity_day = 0;
        this.price_min = 0;
        this.price_max = 0;
        this.search_text = "";
    }

    public String getSearch_text() {
        return search_text;
    }

    public void setSearch_text(String search_text) {
        this.search_text = search_text;
    }

    public int getTouroperator_id() {
        return touroperator_id;
    }

    public void setTouroperator_id(int touroperator_id) {
        this.touroperator_id = touroperator_id;
    }

    public int getTypefood_id() {
        return typefood_id;
    }

    public void setTypefood_id(int typefood_id) {
        this.typefood_id = typefood_id;
    }

    public int getTypetransport_id() {
        return typetransport_id;
    }

    public void setTypetransport_id(int typetransport_id) {
        this.typetransport_id = typetransport_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getTypetour_id() {
        return typetour_id;
    }

    public void setTypetour_id(int typetour_id) {
        this.typetour_id = typetour_id;
    }

    public String getDate_travel() {
        return date_travel;
    }

    public void setDate_travel(String date_travel) {
        this.date_travel = date_travel;
    }

    public int getQuantity_day() {
        return quantity_day;
    }

    public void setQuantity_day(int quantity_day) {
        this.quantity_day = quantity_day;
    }

    public int getPrice_min() {
        return price_min;
    }

    public void setPrice_min(int price_min) {this.price_min = price_min;}

    public int getPrice_max() {
        return price_max;
    }

    public void setPrice_max(int price_max) {
        this.price_max = price_max;
    }
}
