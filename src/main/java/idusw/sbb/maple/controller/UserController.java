  package idusw.sbb.maple.controller;

  import idusw.sbb.maple.common.ApiPaths;
  import idusw.sbb.maple.controller.dto.user.UserRequest;
  import idusw.sbb.maple.exception.HttpException;
  import idusw.sbb.maple.service.UserService;
  import io.swagger.v3.oas.annotations.Operation;
  import jakarta.validation.Valid;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.security.core.Authentication;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.RestController;


  @RestController
  @RequestMapping(ApiPaths.API_V1)
  public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
      this.userService = userService;
    }

    @GetMapping(ApiPaths.USERS)
    @Operation(summary = "Create Route", description = "유저 조회", hidden = true) // hidden 처리
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
      // 유저 조회 컨트롤러는 필요하지 않음에 따라서 주석처리

      throw new HttpException("This is not available. (Don't use this)", HttpStatus.BAD_REQUEST);
      /*
      어느 메서드에서든 UserIdx를 가져올 수 있도록 코드 수정
      if (authentication == null || !authentication.isAuthenticated()) {
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
          }

          Object principal = authentication.getPrincipal();
          if (principal instanceof UserDetail userDetail) {
              return ResponseEntity.ok(userDetail.getUserIdx()); //사용자 객체
          }

          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid principal");
      */

  /*    Long condition = userService.getUserIdx(authentication);

      if (condition == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
      }

      return ResponseEntity.ok(condition);*/
    }


    @PostMapping("/register")
    @Operation(summary = "Create User", description = "회원 가입")
    public ResponseEntity<Void> post(@Valid @RequestBody UserRequest user) {
      userService.createUser(user);
      return ResponseEntity.noContent().build();
    }

    @PostMapping("/check-id") //아이디 확인
    public ResponseEntity<Void> post(@RequestParam String userId) {
      userService.existByUserId(userId);
      return ResponseEntity.noContent().build();
    }

  }
