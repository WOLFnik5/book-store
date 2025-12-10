package store.book.bookstore.service;

import java.util.List;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.BookSearchParametersDto;
import store.book.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
