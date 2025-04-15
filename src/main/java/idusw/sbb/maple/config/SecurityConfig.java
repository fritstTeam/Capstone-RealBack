package idusw.sbb.maple.config;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  // 모든 요청 허용을 위해서 sequrity 기능을 일부 제한했는데 필요하면 고치면 될듯합니다.
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf-> csrf.disable()) // CSRF 끄기 (POST 요청 테스트 편하게)
        .formLogin(formLogin -> formLogin
                .loginProcessingUrl("/login")
                .usernameParameter("id")
                .passwordParameter("pwd")
                .successHandler((request, response, authentication) -> {
                    // 사용자 이름(id)을 쿠키로 저장
                    String username = authentication.getName(); // UserDetail의 getUsername()

                    Cookie cookie = new Cookie("userId", username);
                    cookie.setPath("/"); // 모든 경로에서 접근 가능
                    cookie.setHttpOnly(false); // JS 접근 허용: 필요시 true로
                    cookie.setMaxAge(60 * 60); // 쿠키 유효 시간 (초 단위, 1시간)

                    response.addCookie(cookie);

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().write("로그인 성공");
                })
                .failureHandler((request, response, exception) -> {
                  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                  response.getWriter().write("로그인 실패");
                }))
        .httpBasic(httpBasic -> httpBasic.disable()) // 브라우저 팝업 로그인 끔
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // 모든 요청 허용
        ).cors(c-> c.configurationSource(new CorsConfig()));

    return http.build();
  }
}
