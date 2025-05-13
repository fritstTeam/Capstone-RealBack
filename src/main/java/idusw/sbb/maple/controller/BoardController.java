package idusw.sbb.maple.controller;

import idusw.sbb.maple.common.ApiPaths;
import idusw.sbb.maple.controller.dto.board.BoardResponse;
import idusw.sbb.maple.controller.dto.board.CreateBoardRequest;
import idusw.sbb.maple.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API_V1)
public class BoardController {

  private BoardService boardService;

  BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @PostMapping(ApiPaths.BOARDS)
  public BoardResponse create(
      @RequestBody CreateBoardRequest req
  ) {
    // 유저 구현시까지 임시로 idx 넣음
    Long userIdx = 1L;

    return boardService.create(userIdx, req);
  }


}
