package com.example.cityinfo.model.entity;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "categories")
public class Category extends BaseEntity {

    private String name;
    private String slug;
    private String description;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private List<CategoryField> categoryFields;
    private List<Object> objects;

    public Category() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    @UpdateTimestamp
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<CategoryField> getCategoryFields() {
        return categoryFields;
    }

    public void setCategoryFields(List<CategoryField> categoryField) {
        this.categoryFields = categoryField;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
