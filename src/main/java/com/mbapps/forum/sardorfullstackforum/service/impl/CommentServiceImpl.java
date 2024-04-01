package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentResponse;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumCommentConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public List<ForumCommentResponse> getAllComments() {
    List<ForumCommentDTO> getAllComments = commentRepository.findAll();
    return getAllComments.stream()
        .map(forumCommentConverter::toCommentResponse)//to change ..IdFk to ..Id
        .collect(Collectors.toList());
  }

  @Override
  public List<ForumCommentResponse> getCommentsByPostId(Integer postId) {
    List<ForumCommentDTO> allComments = commentRepository.findAllByPostId(postId);
    return allComments.stream()
        .map(forumCommentConverter::toCommentResponse)//to change ..IdFk to ..Id
        .collect(Collectors.toList());
  }

  @Override
  public ResponseEntity<ForumCommentDTO> createNewComment(ForumCommentModel comment) {
    String dateNow = LocalDateTime.now().toString();
    ForumCommentDTO forumCommentDTO = forumCommentConverter.toCommentDTO(comment);
    comment.setCreatedDate(dateNow);
    int savedResult = commentRepository.save(comment);
    if (savedResult > 0) {
      forumCommentDTO.setStatus(true);
      forumCommentDTO.setCommentId(savedResult);
      return ResponseEntity.status(HttpStatus.CREATED).body(forumCommentDTO);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  @Override
  public void deleteCommentById(Integer id) {
    commentRepository.deleteCommentById(id);
  }

}
