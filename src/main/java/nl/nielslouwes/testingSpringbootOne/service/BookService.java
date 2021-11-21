package nl.nielslouwes.testingSpringbootOne.service;


import nl.nielslouwes.testingSpringbootOne.model.Book;
import nl.nielslouwes.testingSpringbootOne.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    //add methods that you also use in your controller

    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public int addBook(Book book) {
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
