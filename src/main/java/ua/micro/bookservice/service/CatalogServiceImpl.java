package ua.micro.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.micro.bookservice.persistence.entity.Book;
import ua.micro.bookservice.persistence.repository.BookRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final BookRepository bookRepository;

    @Override
    public Book findById(String id) {
        log.info("Retrieving book by id [{}]", id);
        return bookRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Book findByTitle(String title) {
        log.info("Searching book by title [{}]", title);
        return bookRepository.findByTitle(title)
                .orElseThrow();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        log.info("Retrieving all books");
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book add(Book book) {
        log.info("Saving book [{}]", book);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(String id) {
        log.info("Deleting book by id [{}]", id);
        bookRepository.deleteById(id);
    }
}
