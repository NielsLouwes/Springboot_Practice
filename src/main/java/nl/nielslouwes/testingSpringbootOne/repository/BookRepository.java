package nl.nielslouwes.testingSpringbootOne.repository;

import nl.nielslouwes.testingSpringbootOne.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Iterable<Book> findAllByTitle(String title);
    Iterable<Book> findAllByTitleContainingIgnoreCase(String title);
    Iterable<Book> findAllByIsbn(String isbn);

    //TO FIX LATER
    //    @Query("SELECT * FROM student s WHERE s.title LIKE %:s%")
    //    Iterable<Book> searchByTitleLike(@Param("s") String s);

}
