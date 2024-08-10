package com.cykj.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @author abin
 * @date 2024/8/1 11:11
 */
public class JWTUtils {

    private static String secretKey = "D7xLOh30NuCfLN5BocfpZmHn5427xafemtgaowk30vnPrBcd30yFNnzZ6TkwFhkkv3CB9QJg3GyyLo80TFMRDvvggjKxh2MIknWg3uaEWKY3EyF5jJ9cdRTFrPjr03vB";

    private static long expire = 1000 * 60 * 60;

    /**
     * 生成token
     * @param claims 要加密的内容(payload)
     * @return
     */
    public static String generateToken(Map<String, Object> claims){
        String token = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return token;
    }

    public static Claims parseToken(String token) throws ExpiredJwtException {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
