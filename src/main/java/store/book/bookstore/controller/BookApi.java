package store.book.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.BookSearchParametersDto;
import store.book.bookstore.dto.CreateBookRequestDto;
import store.book.bookstore.dto.PageResponse;

@Tag(name = "Books", description = "Endpoints for managing books")
@RequestMapping("/books")
public interface BookApi {

    @Operation(
            summary = "Get all books",
            description = "Retrieve a pagination list of all books with optional sorting")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books")
    @GetMapping
    PageResponse<BookDto> getAll(@PageableDefault(size = 20, sort = "id") Pageable pageable);

    @Operation(
            summary = "Get book by ID",
            description = "Retrieve a specific book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    BookDto getBookById(@Parameter(
            description = "ID of the book to retrieve",
            required = true) @PathVariable Long id);

    @Operation(
            summary = "Create a new book",
            description = "Add a new book to the bookstore")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BookDto createBook(@Parameter(
            description = "Book data created",
            required = true)
                       @Valid @RequestBody CreateBookRequestDto bookDto);

    @Operation(
            summary = "Update a book",
            description = "Update an existing book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully updated"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    BookDto updateBook(
            @Parameter(
                    description = "ID of the book to update",
                    required = true) @PathVariable Long id,
            @Parameter(
                    description = "Update book data",
                    required = true) @Valid @RequestBody CreateBookRequestDto bookDto
    );

    @Operation(
            summary = "Delete a book",
            description = "Delete a book by its ID (soft delete)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(@Parameter(
            description = "ID of the book to delete",
            required = true) @PathVariable Long id);

    @Operation(
            summary = "Search books",
            description = "Search books by various criteria with pagination and sorting")
    @GetMapping("/search")
    PageResponse<BookDto> searchBooks(
            @ParameterObject @Valid BookSearchParametersDto searchParameters,
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    );
}
