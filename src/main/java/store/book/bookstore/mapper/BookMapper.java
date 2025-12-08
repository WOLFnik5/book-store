package store.book.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import store.book.bookstore.dto.BookDto;
import store.book.bookstore.dto.CreateBookRequestDto;
import store.book.bookstore.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);

}
