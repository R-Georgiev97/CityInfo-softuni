package com.example.cityinfo.model.dto;


import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CategorySeedDto {

    @Expose
    private String name;
    @Expose
    private String slug;
    @Expose
    private String description;


    public CategorySeedDto(String name, String slug, String description) {
        this.name = name;
        this.slug = slug;
        this.description = description;
    }


    @Size(min = 3, max = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3, max = 15)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
