package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.CommentBindingModel;
import com.example.cityinfo.model.entity.Comment;
import com.example.cityinfo.model.entity.User;
import com.example.cityinfo.model.entity.enums.UserRoleEnum;
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
        String username = getAuthUsername();

        Comment comment = modelMapper.map(commentBindingModel, Comment.class);
        comment.setObject(objectRepository.getById(commentBindingModel.getObjectId()));
        comment.setUser(userService.getByUsername(username));

        commentRepository.save(comment);
    }

    @Override
    public List<CommentViewModel> parseComments(List<Comment> comments) {
        String username = getAuthUsername();
        return comments
                .stream()
                .map(comment -> {
                    User user = comment.getUser();
                    CommentViewModel commentViewModel = modelMapper.map(comment, CommentViewModel.class);
                    commentViewModel.setAuthor(user.getFirstName() + " " + user.getLastName());
                    commentViewModel.setCreated(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    commentViewModel.setCanEdit(username.equals(user.getUsername()));
                    commentViewModel.setUserAdmin(!username.equals("") && userService.getByUsername(username).getRole().getRole().equals(UserRoleEnum.ADMIN));
                    return commentViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, String content) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new Exception("Comment not found"));
        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Override
    public void destroy(Long id) {
        commentRepository.deleteById(id);
    }

    private String getAuthUsername() {
        String username = "";
        java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        return username;
    }
}
