package idusw.sbb.maple.controller.dto.board;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Schema(description = "Board Response")
@AllArgsConstructor
@Getter
public class BoardResponse {

  @Schema(description = "보드 고유 id", example = "1")
  Long boardIdx;

  @Schema(description = "유저 고유 id", example = "2")
  Long userIdx;

  @Schema(description = "게시글 제목", example = "내가 좋아하는 경로다!")
  String title;

  @Schema(description = "게시글 내용", example = "사실 구라임ㅋㅋ")
  String content;

  @Schema(description = "생성 일자")
  LocalDateTime createdAt;

  @Schema(description = "수정 일자", example = "null", nullable = true)
  LocalDateTime updatedAt;

}
