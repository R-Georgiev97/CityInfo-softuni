package com.example.cityinfo.model.entity;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "categories")
public class Category extends BaseEntity{

    private String name;
    private String description;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Set<CategoryField> categoryFields;

    public Category() {
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    public Set<CategoryField> getCategoryFields() {
        return categoryFields;
    }

    public void setCategoryFields(Set<CategoryField> categoryField) {
        this.categoryFields = categoryField;
    }
}
