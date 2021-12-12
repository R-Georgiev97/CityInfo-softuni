package com.example.cityinfo.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentBindingModel {
    private Long id;
    @NotNull
    @Size(min=4)
    private String content;
    private Long objectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
}
