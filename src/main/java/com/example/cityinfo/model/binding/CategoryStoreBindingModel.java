package com.example.cityinfo.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryStoreBindingModel {

    @NotNull
    @Size(min=4, max=20)
    private String name;
    @NotNull
    @Size(min=4, max=20)
    private String slug;
    @NotNull
    @Size(min=4, max=20)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}