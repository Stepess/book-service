package ua.micro.bookservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.micro.bookservice.persistence.entity.Book;

public interface CatalogService {

    Book findById(String id);

    Book findByTitle(String title);

    Page<Book> findAll(Pageable pageable);

    Book add(Book book);

    void deleteById(String id);

}
