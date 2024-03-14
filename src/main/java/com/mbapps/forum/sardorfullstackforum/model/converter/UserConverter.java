package com.mbapps.forum.sardorfullstackforum.model.converter;

import com.mbapps.forum.sardorfullstackforum.enums.Role;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserModel toUserEntity(UserDTO dto) {
        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
    public UserDTO toUserDTO(UserModel user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole());
        dto.setToken(user.getToken());
        if (user.getRole() == null) {
            dto.setRole(Role.USER);
        }
        return dto;
    }

}
