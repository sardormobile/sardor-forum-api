package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentResponse;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<ForumCommentResponse> getAllComments();
    List<ForumCommentResponse> getCommentsByPostId(Integer postId);
    ResponseEntity<?> createNewComment(ForumCommentModel comment);
    void deleteCommentById(Integer id);

}
