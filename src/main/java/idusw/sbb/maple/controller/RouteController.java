package idusw.sbb.maple.controller;

import idusw.sbb.maple.common.ApiPaths;
import idusw.sbb.maple.common.dto.PaginationRequest;
import idusw.sbb.maple.controller.dto.route.CreateRouteRequest;
import idusw.sbb.maple.controller.dto.route.RouteResponse;
import idusw.sbb.maple.mapper.RouteMapper;
import idusw.sbb.maple.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPaths.API_V1)
public class RouteController {

  private final RouteService routeService;

  public RouteController(RouteService routeService) {
    this.routeService = routeService;
  }

  @PostMapping(ApiPaths.CREATE_ROUTE)
  @Operation(summary = "Create Route", description = "경로 생성")
  public RouteResponse createRoute(
      @RequestBody @Valid CreateRouteRequest req
  ) {
    // 유저 구현 전까지는 Idx 직접 넣음
    Long userIdx = 1L;

    return RouteMapper.toResponse(routeService.createRoute(userIdx, req));
  }

  @GetMapping(ApiPaths.GET_ROUTES)
  @Operation(summary = "Get Routes", description = "경로 조회")
  public List<RouteResponse> getRoutes(
      @ParameterObject PaginationRequest query
  ) {

    return routeService.getRoutes(query).stream()
        .map(RouteMapper::toResponse).toList();// 각각 Route를 RouteResponse로 감싼다;
  }


}
