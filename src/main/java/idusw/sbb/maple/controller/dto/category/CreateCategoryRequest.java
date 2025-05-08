package idusw.sbb.maple.controller.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "카테고리(Category) 생성 Request DTO")
public class CreateCategoryRequest {

  @Size(min = 1, max = 50)
  @Schema(description = "카테고리 이름", example = "즐겨찾기")
  private String name;
}
