package com.mbapps.forum.sardorfullstackforum.service.impl;

import com.mbapps.forum.sardorfullstackforum.model.auth.TokenModel;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserConverter userConverter;
    @Autowired
    UserRepository userRepository;

    AuthenticationProvider authenticationProvider;
//    AuthenticationManager authenticationManager;

    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Override
    public ResponseEntity<?> signUp(UserModel user) { // signUp, new user
        //check username
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        UserModel newUser = new UserModel();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        //save
        UserModel savedUser = userRepository.save(newUser);

        return new ResponseEntity<>(userConverter.toUserDTO(savedUser), HttpStatus.CREATED);
    }

    @Override
    public TokenModel logIn(UserModel user) {// logIn
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserModel userDetails= userRepository.findByUsername(user.getUsername()).orElseThrow();
        String token = null;
        try {
            token = jwtService.generateJwt(userDetails.getUsername());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new TokenModel(
                token,
                userDetails.getUserId(),
                userDetails.getUsername()
        );
//        return userConverter.toUserDTO(result.get());
    }

    @Override
    public List<UserDTO> getAllUsers() {  // get all users
        List<UserModel> users = userRepository.findAll();
        List<UserDTO> newUserList = new ArrayList<>();
        for (UserModel user : users) {
            newUserList.add(userConverter.toUserDTO(user));
        }
        return newUserList;
    }

}
