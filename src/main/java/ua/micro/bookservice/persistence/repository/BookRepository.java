package ua.micro.bookservice.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.micro.bookservice.persistence.entity.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, String> {

    Optional<Book> findByTitle(String title);

}
