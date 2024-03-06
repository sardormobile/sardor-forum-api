package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumPostConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumPostService {
    @Autowired
    ForumPostConverter forumPostConverter;
    @Autowired
    ForumPostRepository forumPostRepository;

    public List<ForumPostDTO> getAllPosts() {
        List<ForumPostModel> allPosts = forumPostRepository.findAll();
        return allPosts.stream()
                .map(forumPostConverter::toForumPostDTO)
                .collect(Collectors.toList());
    }
    public ForumPostDTO createNewPost(ForumPostDTO post) {
//        Optional<UserModel> userOptional = forumPostRepository.findById(requestDTO.getUserId());
        ForumPostModel convertResult = forumPostConverter.toForumPostEntity(post);
        convertResult.setCreatedDate(LocalDateTime.now().toString());
        ForumPostModel saved = forumPostRepository.save(convertResult);

        return forumPostConverter.toForumPostDTO(saved);
    }
    public ResponseEntity deletePostById(Long postId) {
        Optional<ForumPostModel> findResult = forumPostRepository.findById(postId);
        if (findResult.isPresent()) {
            forumPostRepository.deleteForumPostById(postId);
            return new ResponseEntity<>("Post with ID " + postId + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Post with ID " + postId + " does not exist", HttpStatus.NO_CONTENT);
    }

}
