package idusw.sbb.maple.controller;

import idusw.sbb.maple.common.ApiPaths;
import idusw.sbb.maple.controller.dto.route.CreateRouteRequest;
import idusw.sbb.maple.controller.dto.route.CreateRouteResponse;
import idusw.sbb.maple.domain.Route;
import idusw.sbb.maple.mapper.RouteMapper;
import idusw.sbb.maple.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
  public CreateRouteResponse createRoute(
      @RequestBody @Valid CreateRouteRequest req
  ) {
    // 유저 구현 전까지는 Idx 직접 넣었음
    Long userIdx = 1L;

    Route route = routeService.createRoute(userIdx, req);

    return RouteMapper.toResponse(route);
  }


}
