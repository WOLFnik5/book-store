package store.book.bookstore.mapper;

import org.mapstruct.Mapper;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.CreateBookRequestDto;
import store.book.bookstore.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

}
