package store.book.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import store.book.bookstore.dto.CategoryDto;
import store.book.bookstore.model.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
