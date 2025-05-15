package idusw.sbb.maple.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    String token = extractTokenFromCookie(request);
    if (token != null && jwtService.validateToken(token)) { //로그인 이후 요청사용자의 토큰이 유효한가
      String username = jwtService.getUsername(token); //토큰의 사용자정보인 username 추출
      UserDetails userDetails = userDetailsService.loadUserByUsername(
          username); //추출한 username의 기반으로 사용자DB정보 가져옴

      UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      //인증 객체를 생성함

      SecurityContextHolder.getContext().setAuthentication(authToken);
      //SecurityContextHolder에 등록해서, 이후 요청부터는 인증된 사용자로 처리됨
    }

    filterChain.doFilter(request, response);
  }

  private String extractTokenFromCookie(HttpServletRequest request) { // 쿠키에서 토큰 추출
    if (request.getCookies() == null) {
      return null;
    }
    for (Cookie cookie : request.getCookies()) {
      if ("token".equals(cookie.getName())) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
