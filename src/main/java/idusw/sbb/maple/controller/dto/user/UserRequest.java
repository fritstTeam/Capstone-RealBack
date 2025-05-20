package idusw.sbb.maple.controller.dto.user;

import idusw.sbb.maple.validator.PasswordMatches;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@PasswordMatches
@Schema(description = "회원가입 Resquest DTO")
public class UserRequest {

  @Size(min = 1, max = 40, message = "닉네임은 1자 이상 40자 이하로 입력하세요.")
  @Schema(minLength = 1, maxLength = 40)
  String nickname;

  @Size(min = 1, max = 15, message = "아이디는 1자 이상 15자 이하로 입력하세요.")
  @Schema(minLength = 1, maxLength = 15)
  String userId;

  @Size(min = 5, max = 20, message = "비밀번호는 5자 이상 20자 이하로 입력하세요.")
  @Schema(minLength = 5, maxLength = 20)
  String userPassword;

  @NotBlank(message = "비밀번호 확인은 필수입니다.")
  String confirmPassword;
}
