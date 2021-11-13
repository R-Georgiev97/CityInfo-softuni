package com.example.cityinfo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{
    private String name;

    public City(){
    }

    @Column(nullable = false)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
