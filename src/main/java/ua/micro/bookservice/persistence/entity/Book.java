package ua.micro.bookservice.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Document("books")
public class Book {
    @Id
    private String id;
    @Indexed(name = "titleUniqueIndex", unique = true)
    @TextIndexed(weight = 3.f)
    private String title;
    @TextIndexed(weight = 1.f)
    private String description;
    private BigDecimal price;
    private Long quantity;
    @Singular
    private List<Author> authors;
    private List<String> genres;
    private List<String> tags;
}
