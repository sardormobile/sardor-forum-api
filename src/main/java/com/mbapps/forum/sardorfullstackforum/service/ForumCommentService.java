package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumCommentConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.repo.ForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForumCommentService {
    @Autowired
    ForumCommentConverter forumCommentConverter;
    @Autowired
    ForumCommentRepository forumCommentRepository;

    public List<ForumCommentDTO> getAllComments() {
        List<ForumCommentModel> allComments = forumCommentRepository.findAll();
        return allComments.stream()
                .map(forumCommentConverter::toCommentDTO)
                .collect(Collectors.toList());

    }
    public ForumCommentDTO createNewComment(ForumCommentDTO comment) {
        ForumCommentModel convertResult = forumCommentConverter.toCommentEntity(comment);
        convertResult.setCreatedDate(LocalDateTime.now().toString());
        return forumCommentConverter.toCommentDTO(forumCommentRepository.save(convertResult));
    }
    public void deleteCommentById(Long id) {
        forumCommentRepository.deleteById(id);
    }
}
