package store.book.bookstore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "is mandatory")
    @Size(min = 1, max = 255, message = "must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "is mandatory")
    @Size(min = 1, max = 255, message = "must be between 1 and 255 characters")
    private String author;

    @NotBlank(message = "is mandatory")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:?\\ )?"
            + "(?:97[89][ -]?)?[0-9]{1,5}[ -]?[0-9]+[ -]?[0-9]+[ -]?[0-9X]$",
            message = "must be a valid ISBN format")
    private String isbn;

    @NotNull(message = "is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
    private BigDecimal price;

    @Size(max = 1000, message = "must not exceed 1000 characters")
    private String description;

    @Size(max = 255, message = "must not exceed 255 characters")
    private String coverImage;
}
