package idusw.sbb.maple.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public abstract class BaseApiController<T, ID> {
  @PostMapping
  public abstract ResponseEntity<T> create(@RequestBody T dto);

  @PutMapping("/{id}")
  public abstract ResponseEntity<T> update(@PathVariable ID id, @RequestBody T dto);

  @DeleteMapping("/{id}")
  public abstract ResponseEntity<Void> delete(@PathVariable ID id);

  @GetMapping("/{id}")
  public abstract ResponseEntity<T> get(@PathVariable ID id);

  @GetMapping
  public abstract ResponseEntity<List<T>> getAll();

}
