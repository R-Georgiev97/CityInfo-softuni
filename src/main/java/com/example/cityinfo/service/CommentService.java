package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.CommentBindingModel;
import com.example.cityinfo.model.entity.Comment;
import com.example.cityinfo.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    void store(CommentBindingModel commentBindingModel);

    List<CommentViewModel> parseComments(List<Comment> comments);

    void update(Long id,String content) throws Exception;
}
