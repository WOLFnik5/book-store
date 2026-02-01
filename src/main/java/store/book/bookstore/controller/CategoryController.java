package store.book.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import store.book.bookstore.dto.BookDtoWithoutCategoryIds;
import store.book.bookstore.dto.CategoryDto;
import store.book.bookstore.service.BookService;
import store.book.bookstore.service.CategoryService;

@Tag(name = "Categories", description = "Endpoints for managing book categories")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create a new category",
            description = "Create a new book category (Admin only)"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(
            @Parameter(description = "Category data", required = true)
            @Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(
            summary = "Get all categories",
            description = "Retrieve a list of all available categories"
    )
    @GetMapping
    public List<CategoryDto> getAll(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(
            summary = "Get category by ID",
            description = "Retrieve a specific category by its ID"
    )
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(
            @Parameter(description = "ID of the category to retrieve", required = true)
            @PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update a category",
            description = "Update an existing category (Admin only)"
    )
    @PutMapping("/{id}")
    public CategoryDto updateCategory(
            @Parameter(description = "ID of the category to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated category data", required = true)
            @Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete a category",
            description = "Delete a category by ID (Admin only)"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @Parameter(description = "ID of the category to delete", required = true)
            @PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(
            summary = "Get books by category",
            description = "Retrieve all books that belong to a specific category"
    )
    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @Parameter(description = "ID of the category", required = true)
            @PathVariable Long id,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return bookService.findAllByCategoryId(id);
    }
}
