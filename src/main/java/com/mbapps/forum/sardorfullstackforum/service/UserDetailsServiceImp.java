package com.mbapps.forum.sardorfullstackforum.service;//package com.mbapps.forum.sardorfullstackforum.service;

//import com.mbapps.forum.sardorfullstackforum.repo.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
////@Service
//public class UserDetailsServiceImp implements UserDetailsService {
//
//    private final UserRepository repository;
//
//    public UserDetailsServiceImp(UserRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return (UserDetails) repository.findByUsername(username);//.orElseThrow(()-> new UsernameNotFoundException("User not found"));
//    }
//}