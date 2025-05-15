package idusw.sbb.maple.service;

import idusw.sbb.maple.controller.dto.user.UserRequest;
import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.domain.UserDetail;
import idusw.sbb.maple.exception.HttpException;
import idusw.sbb.maple.mapper.UserMapper;
import idusw.sbb.maple.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
    User user = userRepository.findByUserId(id)
        .orElseThrow(() -> new HttpException("Id Or Password is Wrong", HttpStatus.NOT_FOUND));

    return new UserDetail(user.getUserIdx(), user.getUserId(), user.getNickname(),
        user.getUserPassword());
  }

  public void createUser(UserRequest user) {
    existByUserId(user.getUserId());

    String encodedPassword = passwordEncoder.encode(user.getUserPassword());
    User persistence = UserMapper.toPersistence(user, encodedPassword);

    userRepository.save(persistence);
  }

  public void existByUserId(String id) {
    if (userRepository.existsByUserId(id)) {
      throw new HttpException("Id Already Exists", HttpStatus.CONFLICT);
    }
  }
}
