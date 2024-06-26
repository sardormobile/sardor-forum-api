package com.mbapps.forum.sardorfullstackforum.model.converter;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import org.springframework.stereotype.Component;

@Component
public class ForumPostConverter {
  public ForumPostModel toForumPostEntity(ForumPostDTO dto) {
    ForumPostModel newPost = new ForumPostModel();
    UserModel user = new UserModel();
    user.setUserId(dto.getUserId());
    newPost.setTopicIdFk(dto.getTopicIdFk());
    newPost.setUserId(user);
    newPost.setMessage(dto.getMessage());
//        newPost.setCreatedDate(dto.getCreatedDate());
    return newPost;
  }

  public ForumPostDTO toForumPostDTO(ForumPostModel model) {
    ForumPostDTO dto = new ForumPostDTO();
    dto.setPostId(model.getPostId());
    dto.setTopicIdFk(model.getTopicIdFk());
//        dto.setUserId(model.getUserId().getUserId());
//        dto.setRole(model.getUserId().getRole());
    dto.setMessage(model.getMessage());
    dto.setCreatedDate(model.getCreatedDate());
    return dto;
  }
}
