package idusw.sbb.maple.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegisterConfig {

  @Bean
  public FilterRegistrationBean<CookieConfig> cookieFilter() {
    FilterRegistrationBean<CookieConfig> registration = new FilterRegistrationBean<>();
    registration.setFilter(new CookieConfig());
    registration.addUrlPatterns("/*"); // 모든 경로에 필터 적용
    registration.setOrder(1); // 필터 실행 우선순위 (낮을수록 먼저 실행됨)
    return registration;
  }
}
