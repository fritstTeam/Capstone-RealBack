package idusw.sbb.maple.mapper;

import idusw.sbb.maple.controller.dto.route.CreateRouteRequest;
import idusw.sbb.maple.controller.dto.route.RouteResponse;
import idusw.sbb.maple.domain.Category;
import idusw.sbb.maple.domain.Route;
import idusw.sbb.maple.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

public class RouteMapper {

  private static final GeometryFactory geometryFactory = new GeometryFactory();

  public static Route toPersistence(User user, Category category, CreateRouteRequest route) {

    String name = route.getName();
    LineString information = toLineString(route.getInformation());
    String description = route.getDescription();
    LocalDateTime createdAt = LocalDateTime.now();

    return new Route(user, category, name, information, description, createdAt, null);
  }

  public static RouteResponse toResponse(Route route) {

    Long routeIdx = route.getRouteIdx();
    Long userIdx = route.getUserIdx().getUserIdx();
    Long categoryIdx = route.getCategoryIdx().getCategoryIdx();
    String name = route.getName();
    String description = route.getDescription();
    Double[][] information = toCoordinates(route.getInformation());
    LocalDateTime createdAt = route.getCreatedAt();
    LocalDateTime updatedAt = route.getUpdatedAt();

    return new RouteResponse(routeIdx, userIdx, categoryIdx,
        name, information, description, createdAt, updatedAt);
  }


  private static LineString toLineString(List<List<Double>> coordinates) {
    Coordinate[] coords = new Coordinate[coordinates.size()];

    for (int i = 0; i < coordinates.size(); i++) {
      List<Double> point = coordinates.get(i);
      coords[i] = new Coordinate(point.get(0), point.get(1));
    }

    return geometryFactory.createLineString(coords);
  }

  private static Double[][] toCoordinates(LineString lineString) {
    if (lineString == null) {
      return null;
    }
    Coordinate[] coords = lineString.getCoordinates();
    Double[][] coordinates = new Double[coords.length][2];

    for (int i = 0; i < coords.length; i++) {
      coordinates[i][0] = coords[i].x;
      coordinates[i][1] = coords[i].y;
    }

    return coordinates;
  }


}
