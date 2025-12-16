package store.book.bookstore.repository.book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.book.bookstore.model.Book;
import store.book.bookstore.repository.SpecificationProvider;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "author";

    @Override
    public String getKey() {
        return FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(FIELD_NAME), "%" + params[0] + "%");
    }
}
