package store.book.bookstore.repository.book;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.book.bookstore.model.Book;
import store.book.bookstore.repository.SpecificationProvider;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "price";

    @Override
    public String getKey() {
        return FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        BigDecimal min = (params.length > 0
                && !params[0].isBlank()) ? new BigDecimal(params[0]) : null;
        BigDecimal max = (params.length > 1
                && !params[1].isBlank()) ? new BigDecimal(params[1]) : null;

        return (root, query, cb) -> {
            if (min != null && max != null) {
                return cb.between(root.get(FIELD_NAME), min, max);
            } else if (min != null) {
                return cb.greaterThanOrEqualTo(root.get(FIELD_NAME), min);
            } else if (max != null) {
                return cb.lessThanOrEqualTo(root.get(FIELD_NAME), max);
            }
            return cb.conjunction();
        };
    }
}
