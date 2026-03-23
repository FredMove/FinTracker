package com.fintracker.fintacker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secretKey = "Y3sIt5h4rDc0dedS3CRe7keeyButiT5m9petprojectYouJuST5eeTha7Yes0hMB";
    private final long validityMs = 3600000;

    public String generateToken(String username){ //генерация токена
        return Jwts.builder() //иниц процесс создания JWT
                .setSubject(username) //установка поля полезной нагрузки, чтобы потом могли понять чей тьокен
                .setExpiration((new Date(System.currentTimeMillis()+validityMs))) //когда истекает
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) //чем подписываем
                .compact(); //сборка
    }

    public String getUsername(String token){ // а теперь мы достаем из токена пользователя
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())) //проверка подписи
                .build()
                .parseClaimsJws(token) //парс токена
                .getBody()
                .getSubject(); //достаем username, который клали в setSubject на 17й строке
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("JWT validation error: " + e.getMessage()); // добавь это
            return false;
        }
    }
}
