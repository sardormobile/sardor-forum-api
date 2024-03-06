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
        postModel.setPostId(dto.getPostId());
        model.setPostId(postModel);

        UserModel userModel = new UserModel();
        userModel.setUserId(dto.getUserId());
        model.setUserId(userModel);

        model.setMessage(dto.getComment());
//        model.setCreatedDate(dto.getCreatedDate());
        return model;
    }
    public ForumCommentDTO toCommentDTO(ForumCommentModel model) {
        ForumCommentDTO dto = new ForumCommentDTO();
        dto.setCommentId(model.getCommentId());
        dto.setPostId(model.getPostId().getPostId());
        dto.setUserId(model.getUserId().getUserId());
        dto.setComment(model.getMessage());
        dto.setCreatedDate(model.getCreatedDate());
        return dto;
    }
}
