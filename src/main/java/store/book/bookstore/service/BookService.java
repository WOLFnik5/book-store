package store.book.bookstore.service;

import java.util.List;
import store.book.bookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
