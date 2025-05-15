package idusw.sbb.maple.domain;

import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserDetail implements UserDetails {

  public UserDetail(Long userIdx, String userId, String userNickname, String userPassword) {
    this.userIdx = userIdx;
    this.userId = userId;
    this.userNickname = userNickname;
    this.userPassword = userPassword;
  }

  private Long userIdx;
  private String userId;
  private String userNickname;
  private String userPassword;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return userPassword;
  }

  @Override
  public String getUsername() {
    return userId;
  }
}
