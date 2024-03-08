package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.auth.TokenModel;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.service.UserService;
import com.mbapps.forum.sardorfullstackforum.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin//for connect angular in localhost
@RequestMapping("/register")//api/v1
public class AuthController {

    @Autowired
    UserService userService;

//    @Autowired
//    PasswordEncoder encoder;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated @RequestBody UserModel user) {
        return userService.signUp(user);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenModel> logIn(@Validated @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.logIn(user));
    }


}
