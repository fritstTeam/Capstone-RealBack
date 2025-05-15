package idusw.sbb.maple.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_idx")
  private Long userIdx;

  @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(20)")
  private String userId;

  @Column(name = "user_password", nullable = false, columnDefinition = "VARCHAR(100)")
  private String userPassword;

  @Column(name = "nickname", nullable = false, columnDefinition = "VARCHAR(50)")
  private String nickname;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime createdAt;

  // Route 관련
  @OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Route> routes = new ArrayList<>();

  // Board 관련
  @OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Board> boards = new ArrayList<>();

  // Comment 관련
  @OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  public User(String userId, String userPassword, String nickname, LocalDateTime createdAt) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.nickname = nickname;
    this.createdAt = createdAt;
  }
}