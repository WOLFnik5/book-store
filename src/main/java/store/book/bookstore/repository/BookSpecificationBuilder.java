package store.book.bookstore.repository;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.book.bookstore.dto.BookSearchParametersDto;
import store.book.bookstore.model.Book;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String TITLE_KEY = "title";
    private static final String AUTHOR_KEY = "author";
    private static final String ISBN_KEY = "isbn";
    private static final String PRICE_KEY = "price";

    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = Specification.unrestricted();

        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put(TITLE_KEY, searchParameters.title());
        parametersMap.put(AUTHOR_KEY, searchParameters.author());
        parametersMap.put(ISBN_KEY, searchParameters.isbn());

        if (searchParameters.minPrice() != null || searchParameters.maxPrice() != null) {
            String min = searchParameters.minPrice() != null
                    ? searchParameters.minPrice().toString() : "";
            String max = searchParameters.maxPrice() != null
                    ? searchParameters.maxPrice().toString() : "";
            specification = specification.and(
                    bookSpecificationProviderManager
                            .getSpecificationProvider(PRICE_KEY)
                            .getSpecification(new String[]{min, max})
            );
        }

        for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isBlank()) {
                specification = specification.and(
                        bookSpecificationProviderManager
                                .getSpecificationProvider(entry.getKey())
                                .getSpecification(new String[]{entry.getValue()})
                );
            }
        }
        return specification;
    }
}
