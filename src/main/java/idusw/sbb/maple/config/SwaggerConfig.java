package idusw.sbb.maple.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Value("${swagger.env}")
  private String env;

  @Value("${swagger.local-url}")
  private String localUrl;

  @Value("${swagger.develop-url}")
  private String developUrl;

  @Bean
  public OpenAPI openAPI() {
    Server server = new Server();

    String serverUrl = env.equals("local") ? localUrl: developUrl;

    server.setUrl(serverUrl);

    return new OpenAPI()
        .components(new Components())
        .servers(List.of(server))
        .info(apiInfo());

  }


  private Info apiInfo() {
    return new Info()
        .title("Maple API") // API의 제목
        .description("그냥 잘하자 팀의 API 명세서 페이지 입니다.") // API에 대한 설명
        .version("1.0.0"); // API의 버전
  }
}