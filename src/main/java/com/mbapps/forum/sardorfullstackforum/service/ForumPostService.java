package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ForumPostService {
    List<ForumPostDTO> getAllPosts();

    ResponseEntity<ForumPostDTO>  createNewPost(ForumPostDTO post);

    ResponseEntity<String> deletePostById(Integer postId);

    List<ForumCommentModel> getComments(Integer postId);
}
