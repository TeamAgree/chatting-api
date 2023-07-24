package com.agree.chattingapi.utils;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.conf.ApplicationConfig;
import com.agree.chattingapi.entities.UserInfo;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TokenUtils.class);

    private static String secretKey = new ApplicationConfig().getJWTKey();

    public static String generateAccessToken(UserInfo userInfo) {
        String accessToken = Jwts.builder()
                .setHeader(createHeader())
                .setIssuer("https://chatting-app.agree.com")
                .claim("userId", userInfo.getId())
                .claim("roles", userInfo.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000))
                .signWith(createSignature())
                .compact();

        return accessToken;
    }

    public static String reGenerateAccessToken(String refreshToken){
        String accessToken = Jwts.builder()
                .setHeader(createHeader())
                .setIssuer("https://chatting-app.agree.com")
                .claim("userId", getUserIdFromToken(refreshToken))
                .claim("roles", getRolesFromToken(refreshToken))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000))
                .signWith(createSignature())
                .compact();

        return accessToken;
    }

    public static String generateRefreshToken(UserInfo userInfo){

        String refreshToken = Jwts.builder()
                .setHeader(createHeader())
                .setIssuer("https://chatting-app.agree.com")
                .claim("userId", userInfo.getId())
                .claim("roles", userInfo.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000 * 24 * 7))
                .signWith(createSignature())
                .compact();

        return refreshToken;
    }

    public static String parseTokenToUserInfo(String token) {
        return Jwts.parser()
                .setSigningKey(createSignature())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static int isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);

            log.info("expireTime :" + claims.getExpiration());
            log.info("userId :" + claims.get("userId"));
            log.info("roles :" + claims.get("roles"));

            return 0;
        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return 1;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return 2;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return 2;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private static Key createSignature() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .requireIssuer("https://chatting-app.agree.com")
                .build()
                .parseClaimsJws(token).getBody();
    }

    public static String getUserIdFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.get("userId").toString();
    }

    public static String getRolesFromToken(String token){
        Claims claims = getClaimsFormToken(token);
        return claims.get("roles").toString();
    }

}
