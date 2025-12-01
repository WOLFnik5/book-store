package store.book.bookstore;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import store.book.bookstore.model.Book;
import store.book.bookstore.service.BookService;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreApplication {
    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book1 = new Book();
            book1.setTitle("Warriors. Into the wild");
            book1.setAuthor("Erine Hunter");
            book1.setIsbn("978-0062366962");
            book1.setPrice(new BigDecimal("7.99"));
            book1.setDescription(
                    "The first spine-tingling episode in the planned Warriors series. "
                            + "Sure to appeal to followers of Brian Jacques’"
                            + " ongoing Redwall series.");
            book1.setCoverImage("Firepaw.jpg");
            bookService.save(book1);

            Book book2 = new Book();
            book2.setTitle("The Da Vinci Code");
            book2.setAuthor("Dan Brown");
            book2.setIsbn("978-0307474278");
            book2.setPrice(new BigDecimal("6.39"));
            book2.setDescription("Blockbuster perfection.... A gleefully erudite suspense novel");
            book2.setCoverImage("Da-Vinci-Code.jpg");
            bookService.save(book2);

            System.out.println("All books in the database:");
            bookService.findAll().forEach(book ->
                    System.out.println(book.getTitle() + " by" + book.getAuthor()));

        };
    }

}
