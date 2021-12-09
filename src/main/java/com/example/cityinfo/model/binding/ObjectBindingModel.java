package com.example.cityinfo.model.binding;

import com.example.cityinfo.model.entity.ObjectData;
import com.example.cityinfo.model.entity.User;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

public class ObjectBindingModel {
    private Long id;
    @NotNull
    @Size(min=4, max=20)
    private String name;
    @NotNull
    @Size(min=4)
    private String description;
    @NotNull
    private Boolean status;
    @NotNull
    private Long cityId;
    private String cityName;
    private User user;
    @NotNull
    private String categorySlug;
    private List<ObjectData> fields;

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ObjectData> getFields() {
        return fields;
    }

    public void setFields(List<ObjectData> fields) {
        this.fields = fields;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
