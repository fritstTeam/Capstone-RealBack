package idusw.sbb.maple.service;

import idusw.sbb.maple.common.dto.PaginationRequest;
import idusw.sbb.maple.common.dto.PaginationResponse;
import idusw.sbb.maple.common.mapper.PaginationMapper;
import idusw.sbb.maple.controller.dto.route.CreateRouteRequest;
import idusw.sbb.maple.controller.dto.route.RouteResponse;
import idusw.sbb.maple.domain.Category;
import idusw.sbb.maple.domain.Route;
import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.mapper.RouteMapper;
import idusw.sbb.maple.repository.CategoryRepository;
import idusw.sbb.maple.repository.RouteRepository;
import idusw.sbb.maple.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

  private final RouteRepository routeRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  public RouteService(RouteRepository routeRepository, UserRepository userRepository,
      CategoryRepository categoryRepository) {
    this.routeRepository = routeRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
  }

  public Route createRoute(Long userIdx, CreateRouteRequest route) {
    User user = userRepository.findById(userIdx)
        .orElseThrow(() -> new RuntimeException("User not found"));

    Category category = categoryRepository.findById(route.getCategoryIdx())
        .orElseThrow(() -> new RuntimeException("Category not found"));

    return routeRepository.save(RouteMapper.toPersistence(user, category, route));
  }

  public PaginationResponse<RouteResponse> getRoutes(String name, Long categoryIdx,
      PaginationRequest req) {

    Pageable pageable = PaginationMapper.toPageable(req, "createdAt");
    Page<Route> page;

    if (name == null && categoryIdx == null) {
      page = routeRepository.findAll(pageable);
    } else if (name != null && categoryIdx == null) {
      page = routeRepository.findByNameContaining(name, pageable);
    } else if (name == null) {
      page = routeRepository.findByCategoryIdx_CategoryIdx(categoryIdx, pageable);
    } else {
      page = routeRepository.findByNameContainingAndCategoryIdx_CategoryIdx(name, categoryIdx,
          pageable);
    }

    return PaginationResponse.of(
        page.getNumber() + 1,
        page.getSize(),
        page.getTotalElements(),
        page.getContent().stream().map(RouteMapper::toResponse).toList()
    );
  }

}
