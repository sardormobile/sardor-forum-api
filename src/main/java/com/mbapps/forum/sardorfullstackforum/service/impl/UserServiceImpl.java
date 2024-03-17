package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.enums.Role;
import com.mbapps.forum.sardorfullstackforum.exceptions.DataNotFoundException;
import com.mbapps.forum.sardorfullstackforum.model.connection.LogInDTO;
import com.mbapps.forum.sardorfullstackforum.model.connection.UserDTO;
import com.mbapps.forum.sardorfullstackforum.model.converter.UserConverter;
import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.repo.UserRepository;
import com.mbapps.forum.sardorfullstackforum.service.UserService;
import com.mbapps.forum.sardorfullstackforum.service.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;

    private final UserRepository userRepository;

//    private final AuthenticationProvider authenticationProvider;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public ResponseEntity<?> signUp(UserModel user) { // signUp, new user
        //check username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DataNotFoundException("Username already registered!, Id: " + user.getUsername());
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
        int savedUser = userRepository.save(newUser);
        System.out.println("savedUser: " + savedUser);
        UserDTO resUserDto = userConverter.toUserDTO(newUser);
        if (savedUser > 1) {
            resUserDto.setStatus(true);
            resUserDto.setUserId(savedUser);
        }
        return new ResponseEntity<>(resUserDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> logIn(LogInDTO user) {// logIn
        try {
            UserModel userDetails = userRepository.findByUsername(user.getUsername());

            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
            String token = jwtService.generateJwt(userDetails.getUsername());
            UserDTO newUserDTO = userConverter.toUserDTO(userDetails);
            newUserDTO.setToken(token);
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
            UserModel getUserData = userRepository.findByUsername(username);
            if (getUserData != null) {
                UserDTO userDTO = userConverter.toUserDTO(getUserData);
                userDTO.setStatus(true);
                return ResponseEntity.ok(userDTO);
            }
            return ResponseEntity.ok(getUserData);
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
