package store.book.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import store.book.bookstore.dto.UserLoginRequestDto;
import store.book.bookstore.dto.UserLoginResponseDto;
import store.book.bookstore.dto.UserRegistrationRequestDto;
import store.book.bookstore.dto.UserResponseDto;
import store.book.bookstore.exception.RegistrationException;
import store.book.bookstore.security.AuthenticationService;
import store.book.bookstore.service.UserService;

@Tag(name = "Authentication", description = "Endpoints for user authentication")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Register a new user",
            description = "Register a new user account in the bookstore"
    )
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(
            @Parameter(description = "User registration data", required = true)
            @Valid @RequestBody UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @Operation(
            summary = "User authentication",
            description = "Authenticate user and return JWT token"
    )
    @PostMapping("/login")
    public UserLoginResponseDto login(
            @Parameter(description = "User login credentials", required = true)
            @Valid @RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
