package idusw.sbb.maple.common.mapper;

import idusw.sbb.maple.common.dto.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationMapper {

  public static Pageable toPageable(PaginationRequest req, String sortBy) {
    Sort sort = req.isAscending()
        ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();

    return PageRequest.of(req.getPage() - 1, req.getSize(), sort);
  }


}
