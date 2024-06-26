package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentResponse;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/comment")
public class ForumCommentController {

  private final CommentService commentService;
  @GetMapping("/all")
  public ResponseEntity<List<ForumCommentResponse>> getAllComments() {
    return ResponseEntity.ok(commentService.getAllComments());
  }
  @GetMapping("/{postId}/all")
  public ResponseEntity<List<ForumCommentResponse>> getAllCommentsByPostId(@PathVariable("postId") Integer postId) {
    return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
  }

  @PostMapping("/create")
  public ResponseEntity<?> insertNewComment(@Validated @RequestBody ForumCommentModel comment) {
    return new ResponseEntity<>(commentService.createNewComment(comment), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{commentId}")
  public ResponseEntity deleteCommentById(@PathVariable("commentId") Integer commentId) {
    System.out.println("commentId: " + commentId);
    commentService.deleteCommentById(commentId);
    return ResponseEntity.noContent().build();
  }
}
