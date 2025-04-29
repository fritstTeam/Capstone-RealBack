package idusw.sbb.maple.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "정렬 방향", enumAsRef = true)
public enum Direction {
  ASC,
  DESC
}