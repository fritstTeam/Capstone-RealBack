package idusw.sbb.maple.mapper;

import idusw.sbb.maple.controller.dto.category.CategoryResponse;
import idusw.sbb.maple.controller.dto.category.CreateCategoryRequest;
import idusw.sbb.maple.domain.Category;

public class CategoryMapper {

  static public CategoryResponse toResponse(Category category) {
    Long categoryIdx = category.getCategoryIdx();
    String name = category.getName();

    return new CategoryResponse(categoryIdx, name);
  }

  static public Category toPersistence(CreateCategoryRequest category) {
    String name = category.getName();

    return new Category(name);
  }


}
