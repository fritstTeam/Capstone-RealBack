package idusw.sbb.maple.validator;

import idusw.sbb.maple.controller.dto.user.UserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRequest> {

    @Override
    public boolean isValid(UserRequest value, ConstraintValidatorContext context) {
        if (value.getUserPassword() == null || value.getConfirmPassword() == null)
            return true; // 필드 에러는 따로 검증하니까 여기선 true로 둠

        boolean matches = value.getUserPassword().equals(value.getConfirmPassword());

        if (!matches) {
            // 메시지를 필드에 바인딩 (DTO에서 confirmPassword 아래에 오류 표시)
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("비밀번호가 일치하지 않습니다.")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return matches;
    }
}
