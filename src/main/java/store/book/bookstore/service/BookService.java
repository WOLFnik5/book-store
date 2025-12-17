package store.book.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.BookSearchParametersDto;
import store.book.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    Page<BookDto> search(BookSearchParametersDto searchParameters, Pageable pageable);
}
