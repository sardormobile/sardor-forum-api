package com.mbapps.forum.sardorfullstackforum.service.jwt;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.text.ParseException;

public interface JwtService {
  String generateJwt(String username) throws ParseException;

  UsernamePasswordAuthenticationToken validateJwt(String jwt);
}
