package idusw.sbb.maple.service;

import idusw.sbb.maple.controller.dto.category.CategoryResponse;
import idusw.sbb.maple.mapper.CategoryMapper;
import idusw.sbb.maple.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryResponse> getAllCategories() {
    return categoryRepository.findAll().stream().map(CategoryMapper::toResponse).toList();
  }
}
