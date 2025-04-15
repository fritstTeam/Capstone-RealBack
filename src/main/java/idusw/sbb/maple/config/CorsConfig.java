package idusw.sbb.maple.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

public class CorsConfig implements CorsConfigurationSource {


  @Override
  public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true); // 쿠키 허용
    config.addAllowedOriginPattern("*"); // 모든 origin 허용
    config.addAllowedHeader("*"); // 모든 헤더 허용
    config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용 (GET, POST 등)

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return config;
  }
}
