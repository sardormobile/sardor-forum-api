package com.mbapps.forum.sardorfullstackforum.service;


//import com.mbapps.forum.sardorfullstackforum.model.dbDTO.UserModel;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.function.Function;
//
////@Service
//public class JwtService {
//
//    private final String SECRET_KEY = "be38522bf6541926394f11594da8bd680593688d110f56c5e2e7a8efa99ebd14";
//
//    public String extractUserName(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public boolean isValid(String token, UserDetails user) {
//        String username = extractUserName(token);
//        return (username.equals(user.getUsername())) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
//        Claims claims = extracAllClaims(token);
//        return resolver.apply(claims);
//    }
//    private Claims extracAllClaims(String token) {
//        return Jwts
//                .parser()
//                .verifyWith(getSignInKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//    public String generateToken(UserModel registerModel) {
//        return Jwts
//                .builder()
//                .subject(registerModel.getUsername())
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
//                .signWith(getSignInKey())
//                .compact();
//    }
//    private SecretKey getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
