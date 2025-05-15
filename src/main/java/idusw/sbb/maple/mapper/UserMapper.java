package idusw.sbb.maple.mapper;

import idusw.sbb.maple.controller.dto.user.UserRequest;
import idusw.sbb.maple.domain.User;
import java.time.LocalDateTime;

public class UserMapper {

  public static User toPersistence(UserRequest user, String encodedPassword) {

    String userId = user.getUserId();
    String nickname = user.getNickname();
    LocalDateTime createdAt = LocalDateTime.now();

    return new User(userId, encodedPassword, nickname, createdAt);
  }

}
