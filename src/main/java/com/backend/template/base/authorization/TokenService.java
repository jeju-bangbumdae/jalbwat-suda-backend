package com.backend.template.base.authorization;

import com.backend.template.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret-key}")
    private String secretKeyString;
    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private SecretKey secretKey; // final 키워드 제거 (생성자에서 초기화하지 않으므로)

    // 기본 생성자는 비워둡니다. Spring이 빈을 생성할 때 호출됩니다.
    public TokenService() {
    }

    /**
     * 이메일 기반으로 JWT 토큰을 생성합니다.
     * @param user 토큰을 생성할 사용자 객체
     * @return 생성된 JWT 토큰 문자열
     */
    public String generateToken(User user) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyString);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("actor", user.getActor())
                .claim("email", user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long validateTokenAndGetUserId(String token) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secretKeyString);
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);

            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id", Long.class);
        } catch (Exception e) {
            System.err.println("Invalid JWT Token: " + e.getMessage());
            return null;
        }
    }
}