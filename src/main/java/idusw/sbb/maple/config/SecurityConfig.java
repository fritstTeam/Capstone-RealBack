package idusw.sbb.maple.config;


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
        .formLogin(formLogin -> formLogin.disable()) // 기본 로그인 페이지 끔
        .httpBasic(httpBasic -> httpBasic.disable()) // 브라우저 팝업 로그인 끔
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // 모든 요청 허용
        ).cors(c-> c.configurationSource(new CorsConfig()));

    return http.build();
  }
}
