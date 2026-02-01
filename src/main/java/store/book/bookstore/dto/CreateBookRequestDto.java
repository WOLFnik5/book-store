package store.book.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Schema(description = "Request object for creating or updating a book")
public class CreateBookRequestDto {
    @Schema(description = "Book title", example = "The Da Vinci Code",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 1, max = 255, message = "must be between 1 and 255 characters")
    private String title;

    @Schema(description = "Book author", example = "Dan Brown",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 1, max = 255, message = "must be between 1 and 255 characters")
    private String author;

    @Schema(description = "Book ISBN (ISBN-10 or ISBN-13)", example = "978-0307474278",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:?\\\\s*)?"
            + "(?:97[89][ -]?)?"
            + "[0-9]{1,5}[ -]?"
            + "[0-9]+[ -]?"
            + "[0-9]+[ -]?"
            + "[0-9X]$",
            message = "must be a valid ISBN format")
    private String isbn;

    @Schema(description = "Book price", example = "6.39",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
    private BigDecimal price;

    @Schema(description = "Book description",
            example = "Blockbuster perfection.... A gleefully erudite suspense novel")
    @Size(max = 1000, message = "must not exceed 1000 characters")
    private String description;

    @Schema(description = "Book cover image URL",
            example = "Da-Vinci-Code.jpg")
    @Size(max = 255, message = "must not exceed 255 characters")
    private String coverImage;

    private Set<Long> categoryIds = new HashSet<>();
}
