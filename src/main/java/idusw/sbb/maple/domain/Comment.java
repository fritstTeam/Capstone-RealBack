package idusw.sbb.maple.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "comment")
@Getter @Setter
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentIdx;

  @ManyToOne()
  @JoinColumn(name = "user_idx", nullable = false)
  private User userIdx;

  @ManyToOne()
  @JoinColumn(name = "board_idx", nullable = false)
  private Board boardIdx;

  @Column(name = "comment", columnDefinition = "VARCHAR(500)" )
  private String comment;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  private LocalDateTime updatedAt;

  protected Comment() {}
}
