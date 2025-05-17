package idusw.sbb.maple.exception;

import idusw.sbb.maple.common.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 요청시 타입이 잘못됐을 때
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {

    ErrorResponse response = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Not Valid Type. Please Check Type."
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  // @Valid 관련 예외
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException ex) {

    FieldError fieldError = ex.getBindingResult().getFieldError();
    String errorMessage =
        fieldError != null ? fieldError.getDefaultMessage() : "Validation failed.";

    ErrorResponse response = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        errorMessage
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

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

  // Content-Type 관련 예외
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleContentType(HttpMediaTypeNotSupportedException ex) {
    ErrorResponse response = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Wrong Content-Type"
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest request) {
    ex.printStackTrace();

    ErrorResponse response = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Internal Server Error."
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
