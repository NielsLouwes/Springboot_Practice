package nl.nielslouwes.testingSpringbootOne.controller;

import nl.nielslouwes.testingSpringbootOne.model.Book;
import nl.nielslouwes.testingSpringbootOne.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    //attribute
    private List<Book> books = new ArrayList<>();

    //constructor - create the arrayList with preloaded names
    public BookController() {
        Book book1 = new Book();
        book1.setTitle("Harry Potter");
        book1.setAuthor("J.K Rowling");
        book1.setIsbn("930912901");
        books.add(book1);

        Book book2 = new Book();
        book2.setTitle("Harry Potter: Sorcerer's Stone");
        book2.setAuthor("J.K Rowling");
        book2.setIsbn("930912871");
        books.add(book2);
    }
    @Autowired
    private BookRepository bookRepository;

    //responseEntity is another way of writing mapping
    //returns an entity.statuscode(gives us books)
    //GET all books
    @GetMapping(value = "/books")
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    };

    //Get single book
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookRepository.findById(id));
    };

    //DELETE single book
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    };

    //CREATE NEW BOOK
    @PostMapping(value = "/books")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        books.add(book);
        int newId = books.size() - 1;

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    };

    //PUT - Update existing book -ENTIRE THING
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
        books.set(id, book);
        return ResponseEntity.noContent().build();
    };

    //PATCH - Update part of existing book
    @PatchMapping(value = "/books/{id}")
    public ResponseEntity<Object> partialUpdateBook(@PathVariable int id, @RequestBody Book book) {
        Book existingBook = books.get(id);
        if (!book.getTitle().isEmpty()) {
            existingBook.setTitle(book.getTitle());
        }
        if (!book.getAuthor().isEmpty()) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (!book.getIsbn().isEmpty()) {
            existingBook.setIsbn(book.getTitle());
        }
        books.set(id, book);
        return ResponseEntity.noContent().build();
    };
}
