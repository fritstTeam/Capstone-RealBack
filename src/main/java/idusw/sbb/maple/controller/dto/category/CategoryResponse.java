package idusw.sbb.maple.controller.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Category 생성 Response DTO")
public class CategoryResponse {

  @Schema(description = "카테고리 고유 ID", example = "2")
  private Long categoryIdx;

  @Schema(description = "카테고리 이름", example = "즐겨찾기")
  private String name;
}
