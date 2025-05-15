package idusw.sbb.maple.mapper;

import idusw.sbb.maple.controller.dto.user.UserRequest;
import idusw.sbb.maple.domain.User;
import java.time.LocalDateTime;

public class UserMapper {

  public static User toPersistence(UserRequest user, String encodedPassword) {

    String nickname = user.getNickname();
    String userId = user.getUserId();
    LocalDateTime createdAt = LocalDateTime.now();

    return new User(nickname, encodedPassword, userId, createdAt);
  }

}
