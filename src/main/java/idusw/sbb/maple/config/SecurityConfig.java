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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private JwtService jwtService;

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

                    UserDetail userDetail = (UserDetail) authentication.getPrincipal();
                    String jwt = jwtService.generateToken(userDetail.getUsername()); //jwt 토큰 생성 (토큰에 어떤 정보를 넣을것인가)

                    Cookie cookie = new Cookie("token", jwt); //쿠키에 토큰 저장
                    cookie.setHttpOnly(false); // 개발 중 JS에서 토큰 접근 가능하도록
                    cookie.setSecure(false); // HTTPS가 아닌 로컬에서 테스트 가능하도록
                    cookie.setPath("/");
                    response.addCookie(cookie);

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().write("로그인 성공");
                })
                .failureHandler((request, response, exception) -> {
                  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                  response.getWriter().write("로그인 실패");
                }))

            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic(httpBasic -> httpBasic.disable()) // 브라우저 팝업 로그인 끔
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // 모든 요청 허용
        ).cors(c-> c.configurationSource(new CorsConfig()));

    return http.build();
  }
}
