package com.mbapps.forum.sardorfullstackforum.model.db;

import jakarta.persistence.*;

@Entity
@Table(name = "ForumComment")
public class ForumCommentModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name = "postIdFk", referencedColumnName = "postId", nullable = false)
    private ForumPostModel postId;
    @ManyToOne
    @JoinColumn(name = "userIdFk", referencedColumnName = "userId", nullable = false)
    private UserModel userId;
    @Column
    private String message;
    @Column(nullable = false)
    private String createdDate;
    public ForumCommentModel() {}

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public ForumPostModel getPostId() {
        return postId;
    }

    public void setPostId(ForumPostModel postId) {
        this.postId = postId;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
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
