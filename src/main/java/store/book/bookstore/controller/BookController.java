package store.book.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.BookSearchParametersDto;
import store.book.bookstore.dto.CreateBookRequestDto;
import store.book.bookstore.dto.PageResponse;
import store.book.bookstore.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService bookService;

    @Override
    public PageResponse<BookDto> getAll(Pageable pageable) {
        return new PageResponse<>(bookService.findAll(pageable));
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookService.findById(id);
    }

    @Override
    public BookDto createBook(CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto bookDto) {
        return bookService.update(id, bookDto);
    }

    @Override
    public void deleteBook(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public PageResponse<BookDto> searchBooks(BookSearchParametersDto searchParameters,
                                             Pageable pageable) {
        return new PageResponse<>(bookService.search(searchParameters, pageable));
    }
}
