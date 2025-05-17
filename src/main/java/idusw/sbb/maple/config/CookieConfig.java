package idusw.sbb.maple.config;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain chain) throws IOException, ServletException {

    chain.doFilter(req, res); // 먼저 로직 작동 시킨 후, 후에 response값 변경

    if (req instanceof HttpServletRequest request && res instanceof HttpServletResponse response) {
      HttpSession session = request.getSession(false);

      if (session != null) {
        // 커스텀 쿠키 헤더 수동 추가
        response.addHeader("Set-Cookie", String.format(
            "JSESSIONID=%s; Path=/; Domain=.modern-world.shop; HttpOnly; Secure; SameSite=None",
            session.getId()
        ));
      }
    }
  }
}
