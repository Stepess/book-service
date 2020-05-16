package ua.micro.bookservice.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import ua.micro.bookservice.persistence.entity.Author;
import ua.micro.bookservice.persistence.entity.Book;
import ua.micro.bookservice.service.CatalogService;
import ua.micro.bookservice.web.BookController;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
public class BaseContractTestClass {

    @Autowired
    private BookController bookController;

    @MockBean
    private CatalogService catalogService;

    @BeforeEach
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver());
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

        var author = new Author();
        author.setFirstName("Stepan");
        author.setLastName("Yershov");

        var expectedId = "test-contract-id";
        var book = Book.builder()
                .id(expectedId)
                .title("Test Book")
                .description("Awesome book about testing")
                .price(new BigDecimal("42.13"))
                .author(author)
                .tags(List.of("bestseller"))
                .genres(List.of("kek", "lol"))
                .quantity(11L)
                .build();

        when(catalogService.findById(expectedId)).thenReturn(book);

        doNothing().when(catalogService).deleteById(expectedId);

        var bookToSave = Book.builder()
                .title("Test Book")
                .description("Awesome book about testing")
                .price(new BigDecimal("42.13"))
                .author(author)
                .tags(List.of("bestseller"))
                .genres(List.of("kek", "lol"))
                .quantity(11L)
                .build();

        when(catalogService.add(bookToSave)).thenAnswer(invocation -> {
            var savedBook = (Book) invocation.getArgument(0);
            savedBook.setId(expectedId);
            return savedBook;
        });

        when(catalogService.findByTitle("Test Book")).thenReturn(book);

        when(catalogService.findAll(any(Pageable.class))).thenAnswer(invocation -> {
            var pageable = (Pageable) invocation.getArgument(0);
            return new PageImpl<Book>(List.of(book), pageable, 1);
        });
    }

}
