package ua.micro.bookservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.micro.bookservice.persistence.entity.Book;
import ua.micro.bookservice.persistence.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final BookRepository bookRepository;

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
