package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.TopNavBarModel;
import com.mbapps.forum.sardorfullstackforum.service.ForumPostService;
import lombok.RequiredArgsConstructor;
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

  @GetMapping("/all")
  public ResponseEntity<List<ForumPostDTO>> getAllPosts() {
    return ResponseEntity.ok(forumPostService.getAllPosts());
  }
  @GetMapping("/all/{topicId}")
  public ResponseEntity<List<ForumPostDTO>> getAllPostsByTopicId(@PathVariable("topicId") Integer topicId) {
    return ResponseEntity.ok(forumPostService.getAllPostsByTopicId(topicId));
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

  @PostMapping("/nav_item/create")
  public ResponseEntity<List<TopNavBarModel>> createNewNavBarItem(@RequestBody String name) {
    if (forumPostService.insertNewNavBarTitle(name) > 0) {
      return ResponseEntity.ok(forumPostService.getTopTabItems());
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping("/nav_item")
  public ResponseEntity<List<TopNavBarModel>> getNavBarItems() {
    return ResponseEntity.ok(forumPostService.getTopTabItems());
  }

  @DeleteMapping("/nav_item/{index}")
  public ResponseEntity<?> deleteNavBarItem(@PathVariable("index") Integer titleId) {
    if (forumPostService.deleteByTitleById(titleId) > 0) {
      TopNavBarModel topNavBarModel = new TopNavBarModel();
      topNavBarModel.setTopicId(titleId);
      return ResponseEntity.ok(topNavBarModel);
    }
    return ResponseEntity.notFound().build();
  }
}
