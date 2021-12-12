package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.CommentBindingModel;
import com.example.cityinfo.model.entity.Comment;
import com.example.cityinfo.model.view.CommentViewModel;
import com.example.cityinfo.repository.CommentRepository;
import com.example.cityinfo.repository.ObjectRepository;
import com.example.cityinfo.service.CommentService;
import com.example.cityinfo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final ObjectRepository objectRepository;
    private final UserService userService;

    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository, ObjectRepository objectRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.objectRepository = objectRepository;
        this.userService = userService;
    }

    @Override
    public void store(CommentBindingModel commentBindingModel) {
        String username = "";
        java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        Comment comment = modelMapper.map(commentBindingModel, Comment.class);
        comment.setObject(objectRepository.getById(commentBindingModel.getObjectId()));
        comment.setUser(userService.getByUsername(username));

        commentRepository.save(comment);
    }

    @Override
    public List<CommentViewModel> parseComments(List<Comment> comments) {
        return comments
                .stream()
                .map(comment -> {
                    CommentViewModel commentViewModel = modelMapper.map(comment,CommentViewModel.class);
                    commentViewModel.setAuthor(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
                    commentViewModel.setCreated(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    return commentViewModel;
                }).collect(Collectors.toList());
    }
}
