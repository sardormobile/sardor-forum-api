package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.enums.Role;
import com.mbapps.forum.sardorfullstackforum.model.connection.LogInDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.UserConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.repo.UserRepository;
import com.mbapps.forum.sardorfullstackforum.service.UserService;
import com.mbapps.forum.sardorfullstackforum.service.jwt.JwtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserConverter userConverter;
    @Autowired
    UserRepository userRepository;

    AuthenticationProvider authenticationProvider;
//    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Override
    public ResponseEntity<?> signUp(UserModel user) { // signUp, new user
        //check username
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username already registered!");
        }
        UserModel newUser = new UserModel();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() != Role.ADMIN) {
            newUser.setRole(Role.USER);
        }
        String token = null;
        try {
            token = jwtService.generateJwt(user.getUsername());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        newUser.setToken(token);
        //save
        UserModel savedUser = userRepository.save(newUser);
        UserDTO resUserDto = userConverter.toUserDTO(savedUser);
        return new ResponseEntity<>(resUserDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> logIn(LogInDTO user) {// logIn
        try {
            UserModel userDetails = userRepository.findByUsername(user.getUsername()).orElseThrow();

            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
            String token = jwtService.generateJwt(userDetails.getUsername());
            UserDTO newUserDTO = userConverter.toUserDTO(userDetails);
            newUserDTO.setToken(token);
//            return ResponseEntity.status(HttpStatus.OK).body(newUserDTO);
            return ResponseEntity.ok(newUserDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> getUserByUsername(String username) {
        try {
            UserModel getUserData = userRepository.findByUsername(username).orElseThrow();
            return ResponseEntity.ok(userConverter.toUserDTO(getUserData));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {  // get all users
        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(this.userConverter::toUserDTO)
                .collect(Collectors.toList());
    }

}
