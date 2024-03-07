package com.mbapps.forum.sardorfullstackforum.model.auth;

import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import jakarta.persistence.*;

@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    private String token;
//    @Enumerated(EnumType.STRING)
//    private TokenType tokenType;

    private boolean experient;

    private boolean revoke;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

}
