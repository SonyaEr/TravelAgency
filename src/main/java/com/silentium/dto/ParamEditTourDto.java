package com.silentium.dto;

public class ParamEditTourDto {

    private int id;
    private String description;
    private String name;
    private int quantity_night;
    private int typetour_id;
    private int touroperator_id;
    private int typefood_id;
    private int typetransport_id;

    public ParamEditTourDto() {
        this.id = 0;
        this.description = "";
        this.name = "";
        this.typetour_id = -1;
        this.touroperator_id = -1;
        this.typefood_id = -1;
        this.typetransport_id = -1;
        this.quantity_night = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity_night() {
        return quantity_night;
    }

    public void setQuantity_night(int quantity_day) {
        this.quantity_night = quantity_day;
    }

    public int getTypetour_id() {
        return typetour_id;
    }

    public void setTypetour_id(int typetour_id) {
        this.typetour_id = typetour_id;
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
}
