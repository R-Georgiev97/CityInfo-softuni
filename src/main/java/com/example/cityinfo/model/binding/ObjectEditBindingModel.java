package com.example.cityinfo.model.binding;

import com.example.cityinfo.model.entity.CategoryField;
import com.example.cityinfo.model.entity.ObjectData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ObjectEditBindingModel {
    private Long id;
    @NotNull
    @Size(min=4, max=20)
    private String name;
    @NotNull
    @Size(min=4)
    private String description;
    @NotNull
    private Boolean status;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
