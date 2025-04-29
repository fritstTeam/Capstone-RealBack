package idusw.sbb.maple.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
@Schema(description = "Pagination Response DTO")
public class PaginationResponse<T> {

  @Schema(description = "현재 페이지 번호", example = "0")
  private final int page;

  @Schema(description = "페이지 당 데이터 수", example = "10")
  private final int size;

  @Schema(description = "전체 데이터 수", example = "100")
  private final long totalElements;

  @Schema(description = "전체 페이지 수", example = "10")
  private final int totalPages;

  @Schema(description = "조회된 데이터 목록")
  private final List<T> content;

  public PaginationResponse(int page, int size, long totalElements, List<T> content) {
    this.page = page;
    this.size = size;
    this.totalElements = totalElements;
    this.totalPages = calculateTotalPages(totalElements, size);
    this.content = content;
  }

  private int calculateTotalPages(long totalElements, int size) {
    if (size == 0) {
      return 0;
    }
    return (int) Math.ceil((double) totalElements / size);
  }

  public static <Z> PaginationResponse<Z> of(int page, int size, long totalElements,
      List<Z> content) {
    return new PaginationResponse<>(page, size, totalElements, content);
  }
}
