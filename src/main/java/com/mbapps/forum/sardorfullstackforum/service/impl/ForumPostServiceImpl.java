package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.exceptions.DataNotFoundException;
import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumPostConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.model.db.TopNavBarModel;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.repo.ForumPostRepository;
import com.mbapps.forum.sardorfullstackforum.repo.UserRepository;
import com.mbapps.forum.sardorfullstackforum.service.ForumPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumPostServiceImpl implements ForumPostService {

  private final ForumPostConverter forumPostConverter;

  private final ForumPostRepository forumPostRepository;

  private final ForumCommentRepository commentRepository;

  private final UserRepository userRepository;


  @Override
  public List<ForumPostDTO> getAllPosts() {
    List<ForumPostDTO> posts = forumPostRepository.findAll();
    return posts.stream()
        .map(this::mapToForumPostDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<ForumPostDTO> getAllPostsByTopicId(Integer id) {
    List<ForumPostDTO> posts = forumPostRepository.findAllByTopicId(id);

    return posts.stream()
        .map(this::mapToForumPostDTO)
        .collect(Collectors.toList());
  }

  private ForumPostDTO mapToForumPostDTO(ForumPostDTO post) {
    Integer commentsCount = commentRepository.getCommentsCountByPostId(post.getPostId());
    if (commentsCount != null) {
      post.setCommentsCount(commentsCount);
    }
    return post;
  }

  @Override
  public ResponseEntity<ForumPostDTO> createNewPost(ForumPostDTO post) {

    String dateNaw = LocalDateTime.now().toString();

    ForumPostModel convertedForumData = forumPostConverter.toForumPostEntity(post);
    UserModel user = userRepository.findByUserId(post.getUserId());
    if (user == null) {
      throw new DataNotFoundException(post.getPostId());
    }
    convertedForumData.setCreatedDate(dateNaw);

    int savedResult = forumPostRepository.save(convertedForumData);
    Integer commentsCount = commentRepository.getCommentsCountByPostId(post.getPostId());

    if (savedResult > 0) {
      post.setStatus(true);
      post.setPostId(savedResult);
      post.setRole(user.getRole());
      post.setFirstName(user.getFirstName());
      post.setUsername(user.getUsername());
      post.setCreatedDate(dateNaw);
      post.setCommentsCount(commentsCount);
      return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @Override
  public ResponseEntity<String> deletePostById(Integer postId) {
    ForumPostModel postOptional = forumPostRepository.findByPostId(postId);
    if (postOptional != null) {
      forumPostRepository.deleteByPostId(postId);
      return new ResponseEntity<>("Post with ID " + postId + " deleted successfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Post with ID " + postId + " does not exist", HttpStatus.NO_CONTENT);
  }

  @Override
  public List<ForumCommentModel> getComments(Integer postId) {
    ForumPostModel postOptional = forumPostRepository.findByPostId(postId);
    if (postOptional != null) {
      return postOptional.getComments(); // Retrieve the list of comments associated with the post
    }
    // Post not found, handle accordingly (e.g., throw exception or return empty list)
    return Collections.emptyList();
  }

  @Override
  public List<TopNavBarModel> getTopTabItems() {
    String topicFirstItemName = "Home";
    List<TopNavBarModel> topics = forumPostRepository.getTopTabItemsByFirstHome(topicFirstItemName);
    if (topics.isEmpty() || !Objects.equals(topics.get(0).getTopic(), topicFirstItemName)) {
      forumPostRepository.insertNewNavBarTitle(topicFirstItemName);
    }
    return forumPostRepository.getTopTabItemsByFirstHome(topicFirstItemName);
  }

  @Override
  public int deleteByTitle(String title) {
    return forumPostRepository.deleteByTitle(title);
  }

  @Override
  public int insertNewNavBarTitle(String title) {
    return forumPostRepository.insertNewNavBarTitle(title);
  }

}
