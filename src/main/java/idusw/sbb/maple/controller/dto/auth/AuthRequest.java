package idusw.sbb.maple.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "로그인 Resquest DTO")
public class AuthRequest {

  @Size(min = 1, max = 15)
  @Schema(minLength = 1, maxLength = 15)
  String userId;

  @Size(min = 5, max = 20)
  @Schema(minLength = 5, maxLength = 20)
  String userPassword;

}
