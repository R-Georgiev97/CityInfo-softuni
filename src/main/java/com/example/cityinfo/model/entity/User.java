package com.example.cityinfo.model.entity;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private City city;
    private Role role;

    public User() {
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column
    @UpdateTimestamp
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Column
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @ManyToOne()
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
