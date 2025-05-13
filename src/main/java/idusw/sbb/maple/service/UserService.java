package idusw.sbb.maple.service;

import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.domain.UserDetail;
import idusw.sbb.maple.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(id);

        if(user == null) {
            throw new UsernameNotFoundException(id);
        }

        return new UserDetail(user);
    }

    public <T> void userSave(Map userData){ // 회원가입 or 회원수정
        User user = new User();
        user.setUserId(userData.get("userId").toString());
        user.setUserPassword(passwordEncoder.encode(userData.get("password").toString()));
        user.setNickname(userData.get("name").toString());

        System.out.println("서비스user"+user);

        if (userRepository.existsByUserId(userData.get("userId").toString())) {
            throw new IllegalArgumentException("가입 정보를 확인해주세요.");
        }

        userRepository.save(user);
    }

    public boolean existByUserId(String userId){
        return userRepository.existsByUserId(userId);
    }

    public Long getUserIdx(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        } else{
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetail userDetail) {
                return userDetail.getUserIdx(); //사용자 객체
            } else {
                return null;
            }
        }

    }

}
