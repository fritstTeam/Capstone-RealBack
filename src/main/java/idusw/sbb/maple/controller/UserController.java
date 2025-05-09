package idusw.sbb.maple.controller;

import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.repository.UserRepository;
import idusw.sbb.maple.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/api/user")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return ResponseEntity.ok(userDetails.getUsername()); //사용자 객체
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid principal");
    }

    @PostMapping("/check-id") //비밀번호 확인
    public ResponseEntity post(@RequestParam String userId) {
        if(!userService.existByUserId(userId)){
            return ResponseEntity.ok("사용가능한 아이디");
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("이미 사용 중인 아이디입니다.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity post(@RequestBody Map userData){
        try {
            userService.userSave(userData);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }





}
