package com.mrd.tool.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrd.tool.auth.CustomUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    private final long ACCESS_JWT_EXPIRATION = 604800000L;//7 day
    private final long REFRESH_JWT_EXPIRATION = 604800000L;//30day

    public String generateToken(CustomUserDetails userDetails) {
        // Lấy thông tin user
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(userDetails.getUser().getUuid())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String createToken(String content) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + REFRESH_JWT_EXPIRATION);
            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("content", content)
                    .withExpiresAt(expiryDate)
                    .sign(Algorithm.HMAC256(JWT_SECRET));
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
        }
        return null;
    }

    public String createToken(String iss, String sub, String aud, String claimName, String claimValue, String secretKey, Date exp) {
        try {
            return JWT.create()
                    .withIssuer(iss) // iss
                    .withExpiresAt(exp) // exp
                    .withSubject(sub) // sub
                    .withAudience(aud) //aud
                    .withClaim(claimName, claimValue) //room
                    .sign(Algorithm.HMAC256(secretKey));
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
        }
        return null;
    }

    public  String getContentInToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("content").asString();
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
        }
        return null;
    }

//    public static void main(String[] args) {
//        try {
//            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImhpZXVudDg5QGZzb2Z0LmNvbS52biIsImp0aSI6ImYwOTRkNjI4LTBkNzAtNDVkNC05ZTk2LWFhYzFjNzg0NGI4NyIsImV4cCI6MTY1NTkwNjExMywiaXNzIjoiU0tVRUNPU1lTVEVNIiwiYXVkIjoiU0tVRUNPU1lTVEVNIn0.I7QoMGT2ao7yU9Mt8THLqhrkt7vZX1Y-gR2zfS6RlIE";
//            String[] chunks = token.split("\\.");
//            Base64.Decoder decoder = Base64.getUrlDecoder();
//            String header = new String(decoder.decode(chunks[0]));
//            String payload = new String(decoder.decode(chunks[1]));
//            System.out.println(header);
//            System.out.println(payload);
//        } catch (Exception ex) {
//            LOGGER.info(ex.toString());
//        }
//    }

    public String getUuidFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }
}
