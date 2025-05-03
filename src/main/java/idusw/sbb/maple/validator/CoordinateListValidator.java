package idusw.sbb.maple.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class CoordinateListValidator implements
    ConstraintValidator<ValidCoordinates, List<List<Double>>> {


  @Override
  public boolean isValid(List<List<Double>> value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) {
      return false;
    }

    for (List<Double> coordinate : value) {
      if (coordinate == null || coordinate.size() != 2) {
        return false;
      }
      if (coordinate.get(0) == null || coordinate.get(1) == null) {
        return false;
      }

      double lng = coordinate.get(0);
      double lat = coordinate.get(1);

      if (lng < -180 || lng > 180 || lat < -90 || lat > 90) {
        return false;
      }
    }

    return true;
  }
}
