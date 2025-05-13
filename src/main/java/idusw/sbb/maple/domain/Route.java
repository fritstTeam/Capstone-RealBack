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
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.LineString;

@Entity
@Table(name = "route")
@Getter
@Setter
@NoArgsConstructor
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

  @Column(name = "information", nullable = false, columnDefinition = "LineString")
  private LineString information;

  @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(500)")
  private String description;

  @Column(name = "distance", nullable = false, columnDefinition = "DOUBLE")
  private Double distance;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime createdAt;

  @Column(name = "updated_at", columnDefinition = "DATETIME")
  private LocalDateTime updatedAt;

  public Route(User userIdx, Category categoryIdx, String name, LineString information,
      String description, Double distance, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.userIdx = userIdx;
    this.categoryIdx = categoryIdx;
    this.name = name;
    this.information = information;
    this.description = description;
    this.distance = distance;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


}
