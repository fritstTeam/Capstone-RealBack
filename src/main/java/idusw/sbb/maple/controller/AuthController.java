package idusw.sbb.maple.controller;

import idusw.sbb.maple.common.ApiPaths;
import idusw.sbb.maple.controller.dto.auth.AuthRequest;
import idusw.sbb.maple.domain.UserDetail;
import idusw.sbb.maple.exception.HttpException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API_V1)
public class AuthController {

  private final AuthenticationManager authenticationManager;

  public AuthController(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @PostMapping(ApiPaths.LOGIN)
  @Operation(summary = "Login", description = "로그인")
  public ResponseEntity<Void> login(@Valid @RequestBody AuthRequest auth,
      HttpServletRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(auth.getUserId(), auth.getUserPassword()));
    // 인증 정보 SecurityContext에 등록
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 세션 생성 및 SPRING_SECURITY_CONTEXT 저장 → JSESSIONID 자동 발급됨
    HttpSession session = request.getSession(true);
    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

    return ResponseEntity.noContent().build();
  }

  @PostMapping(ApiPaths.LOGOUT)
  @Operation(summary = "Logout", description = "로그아웃")
  public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetail user,
      HttpServletRequest request) {
    if (user == null) {
      throw new HttpException("Authentification failed. Please Login First",
          HttpStatus.UNAUTHORIZED);
    }

    HttpSession session = request.getSession(false);

    if (session != null) {
      session.invalidate();
    }

    return ResponseEntity.noContent().build();
  }

}
