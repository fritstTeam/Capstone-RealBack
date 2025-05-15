package idusw.sbb.maple.controller;

import idusw.sbb.maple.common.ApiPaths;
import idusw.sbb.maple.common.dto.PaginationRequest;
import idusw.sbb.maple.common.dto.PaginationResponse;
import idusw.sbb.maple.controller.dto.route.CreateRouteRequest;
import idusw.sbb.maple.controller.dto.route.GetRoutesResponse;
import idusw.sbb.maple.controller.dto.route.RouteResponse;
import idusw.sbb.maple.domain.UserDetail;
import idusw.sbb.maple.exception.HttpException;
import idusw.sbb.maple.mapper.RouteMapper;
import idusw.sbb.maple.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPaths.API_V1)
public class RouteController {

  private final RouteService routeService;

  public RouteController(RouteService routeService) {
    this.routeService = routeService;
  }

  @PostMapping(ApiPaths.ROUTES)
  @Operation(summary = "Create Route", description = "경로 생성")
  public RouteResponse createRoute(
      @AuthenticationPrincipal UserDetail user,
      @RequestBody @Valid CreateRouteRequest req
  ) {
    if (user == null) {
      throw new HttpException("Authentification failed. Please Login First",
          HttpStatus.UNAUTHORIZED);
    }

    // 유저 구현 전까지는 Idx 직접 넣음
    Long userIdx = user.getUserIdx();

    return RouteMapper.toResponse(routeService.createRoute(userIdx, req));
  }

  @GetMapping(ApiPaths.ROUTES)
  @Operation(summary = "Get Routes", description = "경로 조회")
  public PaginationResponse<GetRoutesResponse> getRoutes(
      @RequestParam(required = false) @Schema(description = "검색할 이름") String name,
      @RequestParam(required = false) @Schema(description = "검색할 카테고리 번호") Long categoryIdx,
      @ParameterObject PaginationRequest query
  ) {
    return routeService.getRoutes(name, categoryIdx, query);
  }

  @GetMapping(ApiPaths.ROUTES + "/{routeIdx}")
  @Operation(summary = "Get Route By Id", description = "경로 Id로 경로 조회")
  public RouteResponse getRouteByIdx(@PathVariable("routeIdx") Long routeIdx) {

    return RouteMapper.toResponse(routeService.getRouteByIdx(routeIdx));
  }

  @DeleteMapping(ApiPaths.ROUTES + "/{routeIdx}")
  @Operation(summary = "Delete Route By Id", description = "경로 Id로 경로 삭제")
  public ResponseEntity<Void> deleteRouteByIdx(@AuthenticationPrincipal UserDetail user,
      @PathVariable("routeIdx") Long routeIdx) {
    if (user == null) {
      throw new HttpException("Authentification failed. Please Login First",
          HttpStatus.UNAUTHORIZED);
    }

    Long userIdx = user.getUserIdx();

    routeService.deleteRouteByIdx(userIdx, routeIdx);

    return ResponseEntity.noContent().build();
  }

}
