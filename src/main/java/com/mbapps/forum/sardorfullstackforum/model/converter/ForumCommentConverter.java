package com.mbapps.forum.sardorfullstackforum.model.converter;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import org.springframework.stereotype.Component;

@Component
public class ForumCommentConverter {
    public ForumCommentModel toCommentEntity(ForumCommentDTO dto) {
        ForumCommentModel model = new ForumCommentModel();
        ForumPostModel postModel = new ForumPostModel();
        postModel.setPostId(dto.getPostIdFk());
        model.setPostId(postModel);

        UserModel userModel = new UserModel();
        userModel.setUserId(dto.getUserIdFk());
        model.setUserId(userModel);

        model.setMessage(dto.getMessage());
//        model.setCreatedDate(dto.getCreatedDate());
        return model;
    }
    public ForumCommentDTO toCommentDTO(ForumCommentModel model) {
        ForumCommentDTO dto = new ForumCommentDTO();
        dto.setCommentId(model.getCommentId());
        dto.setPostIdFk(model.getPostId().getPostId());
        dto.setUserIdFk(model.getUserId().getUserId());
        dto.setRole(model.getUserId().getRole());
        dto.setFirstName(model.getUserId().getFirstName());
        dto.setUsername(model.getUserId().getUsername());
        dto.setMessage(model.getMessage());
        dto.setCreatedDate(model.getCreatedDate());
        return dto;
    }
}
