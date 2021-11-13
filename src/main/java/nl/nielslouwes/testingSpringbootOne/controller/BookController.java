package nl.nielslouwes.testingSpringbootOne.controller;

import nl.nielslouwes.testingSpringbootOne.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //responseEntity is another way of writing mapping
    //returns an entity.statuscode(gives us books)
    //GET all books
    @GetMapping(value = "/books")
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.ok(books);
    };

    //Get single book
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id) {
        return ResponseEntity.ok(books.get(id));
    };

    //Get single book
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        books.remove(id);
        return ResponseEntity.noContent().build();
    };

    //CREATE NEW BOOK
    @PostMapping(value = "/books")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.created(null).build();
    };

    //PUT - Update existing book
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> updateBook(@RequestBody Book book) {
        books.set(id, book);
        return ResponseEntity.noContent().build();
    };
}
