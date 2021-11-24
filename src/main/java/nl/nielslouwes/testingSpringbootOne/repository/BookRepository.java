package nl.nielslouwes.testingSpringbootOne.repository;

import nl.nielslouwes.testingSpringbootOne.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Iterable<Book> findAllByTitle(String title);
    Iterable<Book> findAllByTitleContainingIgnoreCase(String title);

}
