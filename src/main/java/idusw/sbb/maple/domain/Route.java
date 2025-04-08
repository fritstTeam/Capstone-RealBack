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
@Table(name = "route")
@Getter @Setter
public class Route {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "route_idx")
  private Long routeIdx;

  @ManyToOne()
  @JoinColumn(name = "user_idx", nullable = false)
  private User userIdx;

  @ManyToOne()
  @JoinColumn(name = "category_idx", nullable = false)
  private Category categoryIdx;

  @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)")
  private String name;

  // 공간 정보는 LineString으로, 보통 Spatial 타입 사용 (MySQL 기준: geometry or line  string)
  // 이건 추후 조금 손볼 필요가 있을듯. 타입은 따로 Class로 만들든 해야할듯
  @Column(name = "information", nullable = false, columnDefinition = "LineString")
  private String information;

  @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(500)")
  private String description;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  private LocalDateTime updatedAt;

  protected Route() {}
}
