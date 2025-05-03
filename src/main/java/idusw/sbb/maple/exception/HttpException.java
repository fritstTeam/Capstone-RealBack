package idusw.sbb.maple.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

  private final HttpStatus statusCode;

  public HttpException(String message, HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

}