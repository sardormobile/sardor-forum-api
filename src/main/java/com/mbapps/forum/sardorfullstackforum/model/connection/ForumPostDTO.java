package com.mbapps.forum.sardorfullstackforum.model.connection;

import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class ForumPostDTO {
    private Integer postId;
    @NotNull
    private Integer userId;
    @NotNull
    private String message;
    private String createdDate;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
