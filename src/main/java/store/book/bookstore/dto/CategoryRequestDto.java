package store.book.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description
) {
}
