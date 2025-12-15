package store.book.bookstore.dto;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record BookSearchParametersDto(
        String title,
        String author,
        String isbn,
        @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
        BigDecimal minPrice,
        @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
        BigDecimal maxPrice
) {
}
