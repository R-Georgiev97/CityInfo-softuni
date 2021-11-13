package com.example.cityinfo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity{

    private Integer rating;
    private User user;
    private Object object;
    private LocalDateTime updatedAt;

    public Rating() {
    }

    @Column(nullable = false)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    @Column
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
