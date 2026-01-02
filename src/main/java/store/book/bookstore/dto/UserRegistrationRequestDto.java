package store.book.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import store.book.bookstore.validation.FieldMatch;

@Data
@Schema(description = "Request object for user registration")
@FieldMatch(first = "password", second = "repeatPassword",
        message = "The password fields must match")
public class UserRegistrationRequestDto {
    @Schema(description = "User email address", example = "john.doe@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @Schema(description = "User password", example = "securePassword123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @Schema(description = "Repeat password for confirmation", example = "securePassword123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Repeat password is required")
    @Size(min = 8, max = 100, message = "Repeat password must be between 8 and 100 characters")
    private String repeatPassword;

    @Schema(description = "User first name", example = "John",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name must not exceed 255 characters")
    private String firstName;

    @Schema(description = "User last name", example = "Doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name must not exceed 255 characters")
    private String lastName;

    @Schema(description = "User shipping address",
            example = "123 Main St, City, Country")
    @Size(max = 500, message = "Shipping address must not exceed 500 characters")
    private String shippingAddress;
}
