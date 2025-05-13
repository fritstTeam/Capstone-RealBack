package idusw.sbb.maple.controller;

import idusw.sbb.maple.common.ApiPaths;
import idusw.sbb.maple.controller.dto.category.CategoryResponse;
import idusw.sbb.maple.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(ApiPaths.API_V1)
public class CategoryController {

  private final CategoryService categoryService;

  CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping(ApiPaths.CATEGORIES)
  @Operation(summary = "Get Categories", description = "카테고리 조회")
  public List<CategoryResponse> getCategories() {
    return categoryService.getAllCategories();
  }
}
