package idusw.sbb.maple.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

  @GetMapping()
  @Operation(summary = "Get", description = "test 용")
  public ResponseEntity get() {
    return ResponseEntity.ok().build();
  }

  @PostMapping
  @Operation(summary = "Post", description = "test 용")
  public ResponseEntity<Void> post(Object o) {
    return ResponseEntity.created(URI.create("uri만든거")).build();

  }

  @PutMapping
  @Operation(summary = "Put", description = "test 용")
  public ResponseEntity update(Object o, Object dto) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이 메서드는 지원하지 않음");
  }

  @DeleteMapping
  @Operation(summary = "Delete", description = "test 용")
  public ResponseEntity<Void> delete(Object o) {
    return ResponseEntity.noContent().build();
  }

}
