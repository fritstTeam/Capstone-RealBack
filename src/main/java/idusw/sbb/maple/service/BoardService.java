package idusw.sbb.maple.service;

import idusw.sbb.maple.controller.dto.board.BoardResponse;
import idusw.sbb.maple.controller.dto.board.CreateBoardRequest;
import idusw.sbb.maple.domain.Board;
import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.exception.HttpException;
import idusw.sbb.maple.mapper.BoardMapper;
import idusw.sbb.maple.repository.BoardRepository;
import idusw.sbb.maple.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  BoardService(BoardRepository boardRepository, UserRepository userRepository) {
    this.boardRepository = boardRepository;
    this.userRepository = userRepository;
  }

  public BoardResponse create(Long userIdx, CreateBoardRequest req) {

    User user = userRepository.findById(userIdx)
        .orElseThrow(() -> new HttpException("User not found", HttpStatus.NOT_FOUND));

    Board board = boardRepository.save(
        BoardMapper.toPersistence(user, req.getTitle(), req.getContent()));

    return BoardMapper.toResponse(board);
  }
}
