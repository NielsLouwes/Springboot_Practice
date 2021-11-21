package nl.nielslouwes.testingSpringbootOne.controller;

import nl.nielslouwes.testingSpringbootOne.model.Book;
import nl.nielslouwes.testingSpringbootOne.repository.BookRepository;
import nl.nielslouwes.testingSpringbootOne.service.BookService;
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

    @Autowired
    private BookService bookService;

    //responseEntity is another way of writing mapping
    //returns an entity.statuscode(gives us books)
    //GET all books
    @GetMapping(value = "/books")
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    };

    //Get single book
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookService.getBook(id));
    };

    //DELETE single book
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    };

    //CREATE NEW BOOK
    @PostMapping(value = "/books")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
       int newId = bookService.addBook(book);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
       return ResponseEntity.created(location).build();
    };

    //PUT - Update existing book - ENTIRE THING
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
        bookService.updateBook(id, book);

        return ResponseEntity.noContent().build();
    };

    //PATCH - Update part of existing book
    @PatchMapping(value = "/books/{id}")
    public ResponseEntity<Object> partialUpdateBook(@PathVariable int id, @RequestBody Book book) {
        bookService.partialUpdateBook(id, book);

        return ResponseEntity.noContent().build();
    };
}
