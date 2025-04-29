package idusw.sbb.maple.common.dto;

import idusw.sbb.maple.common.Direction;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Pagination Request DTO")
public class PaginationRequest {

  @Min(1)
  @Schema(description = "요청 페이지 번호 (1부터 시작)", defaultValue = "1", example = "1", minimum = "1")
  private Integer page = 1;

  @Max(100)
  @Min(1)
  @Schema(description = "페이지당 데이터 수", defaultValue = "10", example = "10", maximum = "100", minimum = "1")
  private Integer size = 10;

  @Schema(description = "정렬 방향 (ASC/DESC)", example = "DESC", defaultValue = "DESC", implementation = Direction.class)
  private Direction direction;

  public boolean isAscending() {
    return direction == Direction.ASC;
  }


}
