package com.mbapps.forum.sardorfullstackforum.service;

import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.UserConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserConverter userConverter;
    @Autowired
    UserRepository userRepository;

    // get all users
    public List<UserDTO>  getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        List<UserDTO> newUserList = new ArrayList<>();

        for (UserModel user : users) {
            newUserList.add(userConverter.toUserDTO(user));
        }
        return newUserList;
    }

    // signUp, new user
    public UserDTO insertNewUser(UserModel user) {
        UserModel savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    // logIn
    public UserDTO logIn(UserModel user) {
        UserModel result= userRepository.findByUsername(user.getUsername());
        return userConverter.toUserDTO(result);
    }

}
