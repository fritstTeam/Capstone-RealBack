package idusw.sbb.maple.config;


import idusw.sbb.maple.domain.UserDetail;
import idusw.sbb.maple.service.JwtAuthenticationFilter;
import idusw.sbb.maple.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().write("로그인 성공");
                })
                .failureHandler((request, response, exception) -> {
                  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                  response.getWriter().write("로그인 실패");
                }))
            .logout(logout -> logout
                    .logoutUrl("/logout")                        // 로그아웃 요청 URL
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().write("로그아웃 성공");
                    })
                    .invalidateHttpSession(true)                 // 세션 무효화
                    .deleteCookies("JSESSIONID")                 // 세션 쿠키 삭제
            )
        .httpBasic(httpBasic -> httpBasic.disable()) // 브라우저 팝업 로그인 끔
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // 모든 요청 허용
        ).cors(c-> c.configurationSource(new CorsConfig()));

    return http.build();
  }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
