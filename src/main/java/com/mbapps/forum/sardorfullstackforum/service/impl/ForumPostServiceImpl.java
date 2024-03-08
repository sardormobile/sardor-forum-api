package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.ForumPostConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.repo.ForumCommentRepository;
import com.mbapps.forum.sardorfullstackforum.repo.ForumPostRepository;
import com.mbapps.forum.sardorfullstackforum.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumPostServiceImpl implements ForumPostService {
    @Autowired
    ForumPostConverter forumPostConverter;
    @Autowired
    ForumPostRepository forumPostRepository;
    @Autowired
    ForumCommentRepository commentRepository;


    @Override
    public List<ForumPostDTO> getAllPosts() {
        List<ForumPostModel> allPosts = forumPostRepository.findAll();

        return allPosts.stream()
                .map(forumPostConverter::toForumPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ForumPostDTO createNewPost(ForumPostDTO post) {
        ForumPostModel convertResult = forumPostConverter.toForumPostEntity(post);
        convertResult.setCreatedDate(LocalDateTime.now().toString());
        ForumPostModel saved = forumPostRepository.save(convertResult);

        return forumPostConverter.toForumPostDTO(saved);
    }

    @Override
    public ResponseEntity<String> deletePostById(Integer postId) {
        Optional<ForumPostModel> postOptional = forumPostRepository.findById(postId);
        if (postOptional.isPresent()) {
            // Delete all associated comments
            commentRepository.deleteByPostId(postId);
            // Delete the post itself
            forumPostRepository.deleteById(postId);
            return new ResponseEntity<>("Post with ID " + postId + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Post with ID " + postId + " does not exist", HttpStatus.NO_CONTENT);
    }

    @Override
    public List<ForumCommentModel> getComments(Integer postId) {
        Optional<ForumPostModel> postOptional = forumPostRepository.findById(postId);
        if (postOptional.isPresent()) {
            ForumPostModel post = postOptional.get();
            return post.getComments(); // Retrieve the list of comments associated with the post
        } else {
            // Post not found, handle accordingly (e.g., throw exception or return empty list)
            return Collections.emptyList();
        }
    }
}
