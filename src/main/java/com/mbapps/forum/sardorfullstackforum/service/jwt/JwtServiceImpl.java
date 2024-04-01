package com.mbapps.forum.sardorfullstackforum.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

  private final String key = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";//supper_secret_key
  private final SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

  @Override
  public String generateJwt(String username) throws ParseException {
    Date date = new Date();
    long expiration = TimeUnit.MINUTES.toMillis(1000);// 10 minutes in milliseconds;
    Date expiryDate = new Date(date.getTime() + expiration);
    return Jwts.builder()
        .setIssuer("Sardor Forum Server")
        .setSubject("JWT Auth Token")
        .claim("username", username)
        .setIssuedAt(date)
        .setExpiration(expiryDate)
        .signWith(secretKey)
        .compact();
  }

  @Override
  public UsernamePasswordAuthenticationToken validateJwt(String jwt) {
    JwtParser jwtParser = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build();
    Claims claims = jwtParser.parseClaimsJws(jwt).getBody();
    String username = (String) claims.getOrDefault("username", null);
    if (Objects.nonNull(username)) {
      return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
    return null;
  }
}
