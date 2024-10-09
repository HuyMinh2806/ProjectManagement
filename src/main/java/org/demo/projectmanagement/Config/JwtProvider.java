package org.demo.projectmanagement.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 10:36 AM
 */

public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
}
