package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.service.ForumCommentService;
import com.mbapps.forum.sardorfullstackforum.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class ForumCommentController {
    @Autowired
    ForumCommentService forumCommentService;
    @GetMapping("/all")
    public ResponseEntity<List<ForumCommentDTO>> getAllPostComments() {
        return ResponseEntity.ok(forumCommentService.getAllComments());
    }
    @PostMapping("/create")
    public ResponseEntity<ForumCommentDTO> insertNewComment(@Validated @RequestBody ForumCommentDTO comment) {
        return new ResponseEntity<>(forumCommentService.createNewComment(comment), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteComment(@PathVariable("commentId") Long commentId) {
        forumCommentService.deleteCommentById(commentId);
        return ResponseEntity.ok(commentId);
    }
}
