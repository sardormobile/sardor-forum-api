package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.LogInDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> signUp(UserModel user);

    ResponseEntity<?> logIn(LogInDTO user);//UserDTO

    ResponseEntity<?> getUserByUsername(String username);
    List<UserDTO> getAllUsers();


}
