package com.psk_1.psk12.interfaces;
import com.psk_1.psk12.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String>{
    @Query("SELECT b FROM Book b JOIN FETCH b.authors WHERE b.isbn = :isbn")
    Book findBookWithAuthors(@Param("isbn") String isbn);

    @Query("SELECT b FROM Book b JOIN FETCH b.authors")
    List<Book> findAllWithAuthors();
}
