package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.service.ForumPostService;
import com.mbapps.forum.sardorfullstackforum.service.impl.ForumPostServiceImpl;
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
@RequestMapping("/post")
public class ForumPostController {

    private final ForumPostService forumPostService;

    private final ForumCommentRepository forumCommentRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ForumPostDTO>> getAllPosts() {
        return ResponseEntity.ok(forumPostService.getAllPosts());
    }
    @PostMapping("/create")
    public ResponseEntity<ForumPostDTO> createNewPost(@RequestBody @Validated ForumPostDTO post) {
        return forumPostService.createNewPost(post);
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Integer postId) {
        forumPostService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<ForumCommentModel>> getCommentsByPostId(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(forumPostService.getComments(postId));
    }
}
