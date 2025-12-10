package store.book.bookstore.repository.book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.book.bookstore.model.Book;
import store.book.bookstore.repository.SpecificationProvider;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "isbn";

    @Override
    public String getKey() {
        return FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get(FIELD_NAME).in((Object[]) params);
    }
}
