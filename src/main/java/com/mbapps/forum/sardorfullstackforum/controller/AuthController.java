package com.mbapps.forum.sardorfullstackforum.controller;

import com.mbapps.forum.sardorfullstackforum.model.connection.LogInDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")//for connection with angular in localhost
@RequestMapping("/register")//api/v1
public class AuthController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@Validated @RequestBody UserModel user) {
    return userService.signUp(user);
  }

  @PostMapping("/login")
  public ResponseEntity<?> logIn(@Validated @RequestBody LogInDTO user) {
    return userService.logIn(user);
  }

  @PostMapping("/admin")
  public ResponseEntity<?> logInAsAdmin(@Validated @RequestBody LogInDTO user) {
    return userService.logIn(user);
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<?> getUserByUsername(@PathVariable("username") @RequestBody String username) {
    return userService.getUserByUsername(username);
  }
}
