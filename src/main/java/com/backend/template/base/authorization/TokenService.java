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

    /**
     * JWT 토큰의 유효성을 검증하고 이메일을 추출합니다.
     * 실제 사용 시에는 이메일 외에 권한 정보 등도 추출하여 사용합니다.
     * @param token 검증할 JWT 토큰
     * @return 토큰에서 추출된 이메일 (유효하지 않으면 null)
     */
    public String validateTokenAndGetEmail(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // subject 클레임에서 이메일 추출
        } catch (Exception e) {
            System.err.println("Invalid JWT Token: " + e.getMessage());
            return null;
        }
    }
}