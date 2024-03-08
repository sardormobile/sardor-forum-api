package com.mbapps.forum.sardorfullstackforum.model.auth;

import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import jakarta.persistence.*;

@Entity
@Table(name = "Token")
public class Token {
    @Id
    @GeneratedValue
    private Integer tokenId;

    private String token;

    private boolean experience;

    private boolean revoke;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

}
