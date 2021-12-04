package com.example.cityinfo.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CitySeedDto {
    @Expose
    private String name;

    public CitySeedDto(String name) {
        this.name = name;
    }
    @Size(min = 2, max = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
