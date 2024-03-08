package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumCommentConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    ForumCommentConverter forumCommentConverter;
    @Autowired
    ForumCommentRepository commentRepository;


    @Override
    public List<ForumCommentDTO> getAllComments() {
        List<ForumCommentModel> allComments = commentRepository.findAll();
        return allComments.stream()
                .map(forumCommentConverter::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ForumCommentDTO> getCommentsByPostId(Integer postId) {
        List<ForumCommentModel> allComments = commentRepository.findAllByPostId_PostId(postId);
        return allComments.stream()
                .map(forumCommentConverter::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ForumCommentDTO createNewComment(ForumCommentDTO comment) {
        ForumCommentModel convertResult = forumCommentConverter.toCommentEntity(comment);
        convertResult.setCreatedDate(LocalDateTime.now().toString());
        return forumCommentConverter.toCommentDTO(commentRepository.save(convertResult));
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }

}
