package com.example.cityinfo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class ObjectData extends BaseEntity{

    private String fieldKey;
    private String fieldValue;
    private Object object;

    public ObjectData() {
    }

    @Column(nullable = false)
    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @ManyToOne
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
