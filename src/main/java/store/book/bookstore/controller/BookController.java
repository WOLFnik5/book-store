package store.book.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.BookSearchParametersDto;
import store.book.bookstore.dto.CreateBookRequestDto;
import store.book.bookstore.service.BookService;

@Tag(name = "Books", description = "Endpoints for managing books")
@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @Operation(
            summary = "Get all books",
            description = "Retrieve a pagination list of all books with optional sorting"
    )
    @GetMapping
    public Page<BookDto> getAll(
            @Parameter(description = "Pagination and sorting parameters.")
            @PageableDefault(size = 20, sort = "id")
            Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(
            summary = "Get book by ID",
            description = "Retrieve a specific book by its ID"
    )
    @GetMapping("/{id}")
    public BookDto getBookById(
            @Parameter(description = "ID of the book to retrieve", required = true)
            @PathVariable Long id) {
        return bookService.findById(id);
    }

    @Operation(
            summary = "Create a new book",
            description = "Add a new book to the bookstore"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(
            @Parameter(description = "Book data created", required = true)
            @Valid @RequestBody CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @Operation(
            summary = "Update a book",
            description = "Update an existing book by its ID"
    )
    @PutMapping("/{id}")
    public BookDto updateBook(
            @Parameter(description = "ID of the book to update", required = true)
            @ParameterObject
            @PathVariable Long id,
            @Parameter(description = "Update book data", required = true)
            @Valid @RequestBody CreateBookRequestDto bookDto) {
        return bookService.update(id, bookDto);
    }

    @Operation(
            summary = "Delete a book",
            description = "Delete a book by its ID (soft delete)"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @Parameter(description = "ID of the book to delete", required = true)
            @PathVariable Long id) {
        bookService.deleteById(id);
    }

    @Operation(
            summary = "Search books",
            description = "Search books by various criteria with pagination and sorting"
    )
    @GetMapping("/search")
    public Page<BookDto> searchBooks(
            @Parameter(description = "Search criteria")
            @ParameterObject
            @Valid BookSearchParametersDto searchParameters,
            @Parameter(description = "Pagination and sorting parameters")
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }
}
