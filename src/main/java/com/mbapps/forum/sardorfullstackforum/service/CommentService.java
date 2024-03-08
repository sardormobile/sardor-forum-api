package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;

import java.util.List;

public interface CommentService {
    List<ForumCommentDTO> getAllComments();
    List<ForumCommentDTO> getCommentsByPostId(Integer postId);
    ForumCommentDTO createNewComment(ForumCommentDTO comment);
    void deleteCommentById(Integer id);

}
