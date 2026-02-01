package store.book.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        Long id,
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description
) {
}
