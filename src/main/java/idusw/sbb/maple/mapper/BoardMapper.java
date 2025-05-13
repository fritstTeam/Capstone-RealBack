package idusw.sbb.maple.mapper;

import idusw.sbb.maple.controller.dto.board.BoardResponse;
import idusw.sbb.maple.domain.Board;
import idusw.sbb.maple.domain.User;
import java.time.LocalDateTime;

public class BoardMapper {

  public static Board toPersistence(User user, String title, String content) {
    LocalDateTime createdAt = LocalDateTime.now();

    return new Board(user, title, content, createdAt, null);
  }

  public static BoardResponse toResponse(Board board) {
    Long boardIdx = board.getBoardIdx();
    Long userIdx = board.getUserIdx().getUserIdx();
    String title = board.getTitle();
    String content = board.getContent();
    LocalDateTime createdAt = board.getCreatedAt();
    LocalDateTime updatedAt = board.getUpdatedAt();

    return new BoardResponse(boardIdx, userIdx, title, content, createdAt, updatedAt);
  }

}
