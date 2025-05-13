package idusw.sbb.maple.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "board")
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardIdx;

  @ManyToOne()
  @JoinColumn(name = "user_idx", nullable = false)
  private User userIdx;

  @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(100)")
  private String title;

  @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(1000)")
  private String content;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  private LocalDateTime updatedAt;

  // Comment 관련
  @OneToMany(mappedBy = "boardIdx", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  public Board(User user, String title, String content, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.userIdx = user;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
