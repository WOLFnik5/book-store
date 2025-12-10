package store.book.bookstore.repository;

import org.springframework.data.jpa.domain.Specification;
import store.book.bookstore.dto.BookSearchParametersDto;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParametersDto searchParameters);
}
