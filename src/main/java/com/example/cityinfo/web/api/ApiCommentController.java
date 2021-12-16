package com.example.cityinfo.web.api;

import com.example.cityinfo.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class ApiCommentController {

    private CommentService commentService;

    public ApiCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity<String> approveObject(@RequestParam("comment_id") Long commentId,
                                                @RequestParam("content") String content) throws Exception {
        commentService.update(commentId,content);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }

}
