package com.mbapps.forum.sardorfullstackforum.service.jwt;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.text.ParseException;

public interface JwtService {
    String generateJwt(String username) throws ParseException;
    Authentication validateJwt(String jwt);
}
