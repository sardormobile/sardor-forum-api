package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.converter.UserConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin//for connect angular in localhost
@RequestMapping("/register")//api/v1
public class RegisterController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@Validated @RequestBody UserModel user) {
        return new ResponseEntity<>(userService.insertNewUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> logIn(@Validated @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.logIn(user));
    }

}
