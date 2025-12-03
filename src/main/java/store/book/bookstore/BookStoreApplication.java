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
            Book warriors = new Book();
            warriors.setTitle("Warriors. Into the wild");
            warriors.setAuthor("Erine Hunter");
            warriors.setIsbn("978-0062366962");
            warriors.setPrice(new BigDecimal("7.99"));
            warriors.setDescription(
                    "The first spine-tingling episode in the planned Warriors series. "
                            + "Sure to appeal to followers of Brian Jacques’"
                            + " ongoing Redwall series.");
            warriors.setCoverImage("Firepaw.jpg");
            bookService.save(warriors);

            Book daVinciCode = new Book();
            daVinciCode.setTitle("The Da Vinci Code");
            daVinciCode.setAuthor("Dan Brown");
            daVinciCode.setIsbn("978-0307474278");
            daVinciCode.setPrice(new BigDecimal("6.39"));
            daVinciCode.setDescription("Blockbuster perfection.... "
                    + "A gleefully erudite suspense novel");
            daVinciCode.setCoverImage("Da-Vinci-Code.jpg");
            bookService.save(daVinciCode);

            System.out.println("All books in the database:");
            bookService.findAll().forEach(book ->
                    System.out.println(book.getTitle() + " by" + book.getAuthor()));

        };
    }

}
