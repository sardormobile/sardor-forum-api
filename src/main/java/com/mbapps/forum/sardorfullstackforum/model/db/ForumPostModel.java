package com.mbapps.forum.sardorfullstackforum.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ForumPost")
public class ForumPostModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdFk", referencedColumnName = "userId", nullable = false)
    private UserModel userId;
    @Column
    private String message;
    @Column(nullable = false)
    private String createdDate;
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForumCommentModel> comments = new ArrayList<>();

    public ForumPostModel() {}
//    public ForumPostModel(UserModel userId) {
//        this.userId = userId;
//    }

    public List<ForumCommentModel> getComments() {
        return comments;
    }

    public void setComments(List<ForumCommentModel> comments) {
        this.comments = comments;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
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
