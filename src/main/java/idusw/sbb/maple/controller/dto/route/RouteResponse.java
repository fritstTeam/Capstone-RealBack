package idusw.sbb.maple.controller.dto.route;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Route 생성 Response DTO")
public class RouteResponse {

  @Schema(description = "경로 고유 ID", example = "3")
  private Long routeIdx;

  @Schema(description = "유저 고유 ID", example = "1")
  private Long userIdx;

  @Schema(description = "카테고리 고유 ID", example = "1")
  private Long categoryIdx;

  @Schema(description = "이름", example = "내가 좋아하는 경로 A")
  private String name;

  @Schema(
      description = "경로 정보 - LineString WKT 형식",
      example = "[[127.01, 37.5], [127.02, 37.51]]"
  )
  private Double[][] information;

  @Schema(
      description = "설명",
      example = "해당 경로에 대한 설명"
  )
  private String description;

  @Schema(
      description = "생성일",
      example = "2025-04-29T05:06:29.002Z"
  )
  private LocalDateTime createdAt;

  @Schema(
      description = "수정일",
      example = "null",
      nullable = true
  )
  private LocalDateTime updatedAt;
}
