package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.TopNavBarModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ForumPostService {
  List<ForumPostDTO> getAllPosts();
  List<ForumPostDTO> getAllPostsByTopicId(Integer id);
  ResponseEntity<ForumPostDTO> createNewPost(ForumPostDTO post);

  ResponseEntity<String> deletePostById(Integer postId);

  List<ForumCommentModel> getComments(Integer postId);

  List<TopNavBarModel> getTopTabItems();

  int deleteByTitleById(Integer titleId);

  int insertNewNavBarTitle(String title);
}
