package com.example.cityinfo.model.binding;

import javax.validation.constraints.Size;

public class CityBindingModel {
    private Long id;
    @Size(min = 2, max = 25)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
