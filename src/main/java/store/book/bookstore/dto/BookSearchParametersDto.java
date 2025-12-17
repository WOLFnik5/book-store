package store.book.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Schema(description = "Search parameters for filtering books")
public record BookSearchParametersDto(
        @Schema(description = "Filter by book title", example = "The Da Vinci Code")
        String title,

        @Schema(description = "Filter by author name", example = "Dan Brown")
        String author,

        @Schema(description = "Filter by ISBN", example = "978-0307474278")
        String isbn,

        @Schema(description = "Minimum price", example = "6.00")
        @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
        BigDecimal minPrice,

        @Schema(description = "Maximum price", example = "15.00")
        @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
        BigDecimal maxPrice
) {
}
