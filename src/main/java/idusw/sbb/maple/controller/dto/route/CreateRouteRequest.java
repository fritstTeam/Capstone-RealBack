package idusw.sbb.maple.controller.dto.route;

import idusw.sbb.maple.validator.ValidCoordinates;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "경로(Route) 생성 Resquest DTO")
public class CreateRouteRequest {

  @NotNull
  @Schema(description = "카테고리 고유 ID", example = "3")
  private Long categoryIdx;

  @Size(max = 100)
  @NotBlank
  @Schema(description = "경로 이름", example = "내가 좋아하는 경로 A")
  private String name;

  @ValidCoordinates(message = "Coordinates Are Not Valid. Please Check Again.")
  @Schema(
      description = "경로 정보 - LineString WKT 형식",
      example = "[[127.01, 37.5], [127.02, 37.51]]"
  )
  private List<List<Double>> information;

  @Size(max = 500)
  @NotBlank
  @Schema(description = "경로 설명", example = "초보자에게 적합한 경로입니다.")
  private String description;


}

