package com.example.cityinfo.model.view;


import com.example.cityinfo.model.entity.CategoryField;
import com.example.cityinfo.model.entity.ObjectData;
import java.util.List;

public class ObjectViewModel {
    private Long id;
    private String name;
    private String description;
    private Boolean status;
    private String cityName;
    private String categoryName;
    private List<ObjectData> fields;
    private Double averageRating;
    private Boolean canBeEdited;

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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ObjectData> getFields() {
        return fields;
    }

    public void setFields(List<ObjectData> fields) {
        this.fields = fields;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Boolean getCanBeEdited() {
        return canBeEdited;
    }

    public void setCanBeEdited(Boolean canBeEdited) {
        this.canBeEdited = canBeEdited;
    }

}
