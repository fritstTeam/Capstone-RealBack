package idusw.sbb.maple.service;

import idusw.sbb.maple.common.dto.PaginationRequest;
import idusw.sbb.maple.common.dto.PaginationResponse;
import idusw.sbb.maple.common.mapper.PaginationMapper;
import idusw.sbb.maple.controller.dto.route.CreateRouteRequest;
import idusw.sbb.maple.controller.dto.route.GetRoutesResponse;
import idusw.sbb.maple.domain.Category;
import idusw.sbb.maple.domain.Route;
import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.exception.HttpException;
import idusw.sbb.maple.mapper.RouteMapper;
import idusw.sbb.maple.repository.CategoryRepository;
import idusw.sbb.maple.repository.RouteRepository;
import idusw.sbb.maple.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        .orElseThrow(() -> new HttpException("User not found", HttpStatus.NOT_FOUND));

    Category category = categoryRepository.findById(route.getCategoryIdx())
        .orElseThrow(() -> new HttpException("Category not found", HttpStatus.NOT_FOUND));

    return routeRepository.save(RouteMapper.toPersistence(user, category, route));
  }

  public PaginationResponse<GetRoutesResponse> getRoutes(String name, Long categoryIdx,
      PaginationRequest req) {

    Pageable pageable = PaginationMapper.toPageable(req, "createdAt");
    Page<Route> page = getRoutePage(name, categoryIdx, pageable);

    return PaginationResponse.of(
        page.getNumber() + 1,
        page.getSize(),
        page.getTotalElements(),
        page.getContent().stream().map(RouteMapper::toGetRoutesResponse).toList()
    );
  }

  private Page<Route> getRoutePage(String name, Long categoryIdx, Pageable pageable) {
    if (name == null && categoryIdx == null) {
      return routeRepository.findAll(pageable);
    } else if (name != null && categoryIdx == null) {
      return routeRepository.findByNameContaining(name, pageable);
    } else if (name == null) {
      return routeRepository.findByCategoryIdx_CategoryIdx(categoryIdx, pageable);
    } else {
      return routeRepository.findByNameContainingAndCategoryIdx_CategoryIdx(name, categoryIdx,
          pageable);
    }
  }

  public Route getRouteByIdx(Long routeIdx) {

    return routeRepository.findById(routeIdx)
        .orElseThrow(() -> new HttpException("Route not found", HttpStatus.NOT_FOUND));
  }

  public void deleteRouteByIdx(Long userIdx, Long routeIdx) {
    userRepository.findById(userIdx)
        .orElseThrow(() -> new HttpException("User not found.", HttpStatus.NOT_FOUND));

    Route route = routeRepository.findById(routeIdx)
        .orElseThrow(() -> new HttpException("Route not found.", HttpStatus.NOT_FOUND));

    if (!userIdx.equals(route.getUserIdx().getUserIdx())) {
      throw new HttpException("You are not allowed to delete this route.", HttpStatus.FORBIDDEN);
    }

    routeRepository.deleteById(routeIdx);
  }
}
