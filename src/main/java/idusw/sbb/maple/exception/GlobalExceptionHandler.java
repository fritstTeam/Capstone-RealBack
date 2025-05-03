package idusw.sbb.maple.exception;

import idusw.sbb.maple.common.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 사용자 정의 예외
  @ExceptionHandler(HttpException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(HttpException ex) {
    int statusCode = ex.getStatusCode().value();

    ErrorResponse response = new ErrorResponse(
        statusCode,
        ex.getMessage()
    );

    return ResponseEntity.status(statusCode).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest request) {

    ErrorResponse response = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Internal Server Error."
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
