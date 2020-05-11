package ua.micro.bookservice.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.micro.bookservice.persistence.entity.Author;
import ua.micro.bookservice.persistence.entity.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookRepositoryIntegrationTest {

    @Autowired
    BookRepository repository;

    @Test
    void shouldInsertNewBook() {
        var author = new Author();
        author.setFirstName("Stepan");
        author.setLastName("Yershov");

        var book = Book.builder()
                .title("Test Book")
                .description("Awesome book about testing")
                .price(new BigDecimal("42.13"))
                .author(author)
                .tags(List.of("bestseller"))
                .genres(List.of("kek", "lol"))
                .quantity(11L)
                .build();

        var savedBook = repository.save(book);

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());

        var retrievedBook = repository.findById(savedBook.getId());

        assertEquals(book, retrievedBook.orElseThrow());
    }
}