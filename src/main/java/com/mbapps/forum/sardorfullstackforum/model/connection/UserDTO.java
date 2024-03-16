package com.mbapps.forum.sardorfullstackforum.model.connection;


import com.mbapps.forum.sardorfullstackforum.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Boolean status = false;
    private Integer userId;
    private Role role;
    private String firstName;
    private String lastName;
    private String username;
    private String token;

}

