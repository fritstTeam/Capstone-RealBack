package idusw.sbb.maple.common;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ErrorResponse {

  private int statusCode;
  private String message;
  private LocalDateTime timestamp;

  public ErrorResponse(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }
}
