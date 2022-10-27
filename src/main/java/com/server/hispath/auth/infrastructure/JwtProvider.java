package com.server.hispath.auth.infrastructure;

import java.util.Date;

import com.server.hispath.auth.domain.Member;
import com.server.hispath.exception.authorization.InvalidTokenException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class JwtProvider {

    @Value("${jwt.student-secret-key}")
    private String studentSecretKey;

    @Value("${jwt.manager-secret-key}")
    private String managerSecretKey;

    @Value("${jwt.expired-time}")
    private Long expiredTime;

    public String createToken(String payload, Member member) {
        Date now = new Date();
        Date expiredDay = new Date(now.getTime() + expiredTime);
        Claims claims = Jwts.claims().setSubject(payload);

        return Jwts.builder()
                   .setIssuedAt(now)
                   .setIssuer("MyPath")
                   .setExpiration(expiredDay)
                   .setClaims(claims)
                   .signWith(SignatureAlgorithm.HS256, getSecretKey(member))
                   .compact();

    }

    public String getSecretKey(Member member){
        if(Member.isStudent(member)) return studentSecretKey;
        return managerSecretKey;
    }

    public boolean validateBothToken(String token){

        try{
            validateToken(token, Member.STUDENT);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            try{
                validateToken(token, Member.MANAGER);
                return true;
            } catch (JwtException | IllegalArgumentException e2){
                return false;
            }
        }
    }

    public void validateToken(String token, Member member) {
        try {
            JwtParser jwtParser = getJwtParser(member);
            jwtParser.parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    public String getPayLoad(String token, Member member) {
        JwtParser jwtParser = getJwtParser(member);
        return jwtParser.parseClaimsJws(token)
                        .getBody()
                        .getSubject();
    }

    private JwtParser getJwtParser(Member member) {
        return Jwts.parser()
                   .setSigningKey(getSecretKey(member));
    }

    public boolean isValidToken(String token, Member member) {
        try {
            validateToken(token, member);
            return true;
        } catch (InvalidTokenException e) {
            return false;
        }
    }
}
