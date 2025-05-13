package idusw.sbb.maple.controller.dto.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "게시글 생성 DTO")
public class CreateBoardRequest {

  @Size(min = 1, max = 90)
  @Schema(minLength = 1, maxLength = 90)
  private String title;

  @Size(min = 1, max = 800)
  @Schema(minLength = 1, maxLength = 800)
  private String content;
}
