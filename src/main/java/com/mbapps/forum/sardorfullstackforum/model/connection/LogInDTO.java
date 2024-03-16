package com.mbapps.forum.sardorfullstackforum.model.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogInDTO {
    private Boolean status = false;
    private String username;
    private String password;
}
