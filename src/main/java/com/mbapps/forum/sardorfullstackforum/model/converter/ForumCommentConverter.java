package com.mbapps.forum.sardorfullstackforum.model.converter;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentResponse;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ForumCommentConverter {
    public ForumCommentModel toCommentEntity(ForumCommentDTO dto) {
        ForumCommentModel model = new ForumCommentModel();
        ForumPostModel postModel = new ForumPostModel();
        postModel.setPostId(dto.getPostIdFk());
//        model.setPostIdFk(postModel.getPostId());

        UserModel userModel = new UserModel();
        userModel.setUserId(dto.getUserIdFk());
        model.setUserId(userModel.getUserId());

        model.setMessage(dto.getMessage());
//        model.setCreatedDate(dto.getCreatedDate());
        return model;
    }
    public ForumCommentDTO toCommentDTO(ForumCommentModel model) {
        ForumCommentDTO res = new ForumCommentDTO();
        res.setCommentId(model.getCommentId());
        res.setPostIdFk(model.getPostId());
        res.setUserIdFk(model.getUserId());
        res.setMessage(model.getMessage());
        res.setCreatedDate(model.getCreatedDate());
        return res;
    }
    public ForumCommentResponse toCommentResponse(ForumCommentDTO model) {
        ForumCommentResponse res = new ForumCommentResponse();
        BeanUtils.copyProperties(model, res);
        res.setPostId(model.getPostIdFk());
        res.setUserId(model.getUserIdFk());
        return res;
    }
}
