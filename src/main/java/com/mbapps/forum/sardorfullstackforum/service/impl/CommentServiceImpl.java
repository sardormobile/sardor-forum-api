package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumCommentConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ForumCommentConverter forumCommentConverter;

    private final ForumCommentRepository commentRepository;


    @Override
    public List<ForumCommentDTO> getAllComments() {
        List<ForumCommentDTO> allComments = commentRepository.findAll();
        return allComments;
//        return allComments.stream()
//                .map(forumCommentConverter::toCommentDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public List<ForumCommentDTO> getCommentsByPostId(Integer postId) {
        List<ForumCommentModel> allComments = commentRepository.findAllByPostId(postId);
        return allComments.stream()
                .map(forumCommentConverter::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ForumCommentDTO createNewComment(ForumCommentDTO comment) {
        ForumCommentModel convertResult = forumCommentConverter.toCommentEntity(comment);
        convertResult.setCreatedDate(LocalDateTime.now().toString());
        int savedResult = commentRepository.save(convertResult);
        if (savedResult == 1) {
            comment.setStatus(true);
        }
        return comment;
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }

}
