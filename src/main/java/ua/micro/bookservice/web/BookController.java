package ua.micro.bookservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import ua.micro.bookservice.persistence.entity.Book;
import ua.micro.bookservice.service.CatalogService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final CatalogService catalogService;

    @GetMapping
    public Page<Book> getCatalogPage(@SortDefault.SortDefaults({
            @SortDefault(sort = "title", direction = Sort.Direction.ASC)}) Pageable pageable) {
        return catalogService.findAll(pageable);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return catalogService.add(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        catalogService.deleteById(id);
    }

    @GetMapping(params = "title")
    public Book findByTitle(@RequestParam String title) {
        return catalogService.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable String id) {
        return catalogService.findById(id);
    }

}
