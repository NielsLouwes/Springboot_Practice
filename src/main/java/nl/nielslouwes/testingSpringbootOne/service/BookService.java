package nl.nielslouwes.testingSpringbootOne.service;


import nl.nielslouwes.testingSpringbootOne.dto.BookRequestDto;
import nl.nielslouwes.testingSpringbootOne.exception.BadRequestException;
import nl.nielslouwes.testingSpringbootOne.exception.RecordNotFoundException;
import nl.nielslouwes.testingSpringbootOne.model.Book;
import nl.nielslouwes.testingSpringbootOne.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //return all books or book based on title
    public Iterable<Book> getBooks(String title) {
        if (title.isEmpty()) {
            return bookRepository.findAll();
        }
        else {
            return bookRepository.findAllByTitleContainingIgnoreCase(title);
        }
    }

    public Book getBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            // exception of case when book doesn't exist
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public int addBook(BookRequestDto bookRequestDto) {

        String isbn = bookRequestDto.getIsbn();
        List<Book> books = (List<Book>)bookRepository.findAllByIsbn(isbn);
        if (books.size() > 0) {
            throw new BadRequestException("Isbn already exists!!!");
        }

        Book book = new Book();
        book.setAuthor(bookRequestDto.getAuthor());
        book.setTitle(bookRequestDto.getTitle());
        book.setIsbn(bookRequestDto.getIsbn());

        Book newBook = bookRepository.save(book);
        return newBook.getId();
    }

    public void updateBook(int id, Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (!book.getTitle().isEmpty()) {
            existingBook.setTitle(book.getTitle());
        }
        if (!book.getAuthor().isEmpty()) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (!book.getIsbn().isEmpty()) {
            existingBook.setIsbn(book.getTitle());
        }
        bookRepository.save(existingBook);
    }

    public void partialUpdateBook(int id , Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (!(book.getTitle() == null) && !book.getTitle().isEmpty()) {
            existingBook.setTitle(book.getTitle());
        }
        if (!(book.getAuthor() == null ) && !book.getAuthor().isEmpty()) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (!(book.getIsbn() == null) && !book.getIsbn().isEmpty()) {
            existingBook.setIsbn(book.getTitle());
        }
        bookRepository.save(existingBook);
    }
}
