package store.book.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.data.domain.Page;

@Schema(description = "Paginated response wrapper")
public record PageResponse<T>(
        @Schema(description = "List of items on the current page")
        List<T> content,
        @Schema(description = "Current page number (0-based)")
        int page,
        @Schema(description = "Page size")
        int size,
        @Schema(description = "Total number of elements")
        long totalElements,
        @Schema(description = "Total number of pages")
        int totalPages) {
    public PageResponse(Page<T> page) {
        this(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
