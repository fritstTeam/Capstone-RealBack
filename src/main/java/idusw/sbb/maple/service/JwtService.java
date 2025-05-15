package idusw.sbb.maple.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

  SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String generateToken(String username) { //로그인 성공시 jwt 토큰생성후 클라이언트 전달
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
  }


  public boolean validateToken(String token) { //토큰이 유효한지 검증 , 클라이언트가 보낸 토큰이 요청한 사용자의 것인지 확인
    try {
      Jwts.parser().setSigningKey(key).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getUsername(
      String token) { //토큰에서 사용자 id 추출 , 토큰에서 유저정보를 꺼내 , UserDetails 객체 생성할때 필요
    return Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public Claims extractAllClaims(String token) { // 사용자의 추가정보 (닉네임,생일 등 개인정보 필요할때)
    return Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token)
        .getBody();
  }
}
