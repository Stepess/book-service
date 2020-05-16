package ua.micro.bookservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ua.micro.bookservice.persistence.entity.Author;
import ua.micro.bookservice.persistence.entity.Book;
import ua.micro.bookservice.persistence.repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogServiceImplTest {

    @Mock
    BookRepository repository;

    @InjectMocks
    CatalogServiceImpl catalogService;

    @Test
    void shouldFindBookById() {
        var testId = "testId";

        var expectedBook = getBookWithId(testId);

        when(repository.findById(testId)).thenReturn(
                Optional.of(expectedBook));

        var actualBook = catalogService.findById(testId);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundById() {
        var testId = "testId";

        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> catalogService.findById(testId));
    }

    @Test
    void shouldFindBookByTitle() {
        var title = "title";

        var expectedBook = getBookWithTitle(title);

        when(repository.findByTitle(title)).thenReturn(
                Optional.of(expectedBook));

        var actualBook = catalogService.findByTitle(title);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundByTitle() {
        var title = "title";

        when(repository.findByTitle(title)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> catalogService.findByTitle(title));
    }

    @Test
    void shouldDeleteBookById() {
        var testId = "testId";

        doNothing().when(repository).deleteById(testId);

        catalogService.deleteById(testId);

        verify(repository, times(1)).deleteById(testId);
    }

    @Test
    void shouldFindAll() {
        var pageable = mock(Pageable.class);

        var expectedBook = getBookWithId("test_id");

        when(repository.findAll(pageable))
                .thenReturn(new PageImpl<>(List.of(expectedBook)));

        var books = catalogService.findAll(pageable);

        assertEquals(books.getContent(), List.of(expectedBook));
    }

    @Test
    void shouldSaveBook() {
        var expectedId = "test_id";
        var bookToSave = getBookWithId(null);

        when(repository.save(bookToSave)).thenAnswer(invocation -> {
            var savedBook = (Book) invocation.getArgument(0);
            savedBook.setId(expectedId);
            return savedBook;
        });

        var savedBook = catalogService.add(bookToSave);

        bookToSave.setId(expectedId);
        assertEquals(bookToSave, savedBook);
    }

    Book getBookWithId(String id) {
        var author = new Author();
        author.setFirstName("Stepan");
        author.setLastName("Yershov");

        return Book.builder()
                .id(id)
                .title("Test Book")
                .description("Awesome book about testing")
                .price(new BigDecimal("42.13"))
                .author(author)
                .tags(List.of("bestseller"))
                .genres(List.of("kek", "lol"))
                .quantity(11L)
                .build();
    }

    Book getBookWithTitle(String title) {
        var author = new Author();
        author.setFirstName("Stepan");
        author.setLastName("Yershov");

        return Book.builder()
                .id("test-id")
                .title(title)
                .description("Awesome book about testing")
                .price(new BigDecimal("42.13"))
                .author(author)
                .tags(List.of("bestseller"))
                .genres(List.of("kek", "lol"))
                .quantity(11L)
                .build();
    }
}