package com.example.cityinfo.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CategoryBindingModel {
    private Long id;
    @NotNull
    @Size(min=4, max=20)
    private String name;
    @NotNull
    @Size(min=4, max=20)
    private String slug;
    @NotNull
    @Size(min=4, max=20)
    private String description;
    private String[] fieldNames;
    private String[] fieldSlugs;
    private List<CategoryFieldBindingModel> categoryFields;

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

    public String[] getFieldNames() {
        if (fieldNames == null){
            return new String[0];
        }
        return fieldNames;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String[] getFieldSlugs() {
        if (fieldSlugs == null){
            return new String[0];
        }
        return fieldSlugs;
    }

    public void setFieldSlugs(String[] fieldSlugs) {
        this.fieldSlugs = fieldSlugs;
    }

    public List<CategoryFieldBindingModel> getCategoryFields() {
        return categoryFields;
    }

    public void setCategoryFields(List<CategoryFieldBindingModel> categoryFields) {
        this.categoryFields = categoryFields;
    }


}