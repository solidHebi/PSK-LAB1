package com.psk_1.psk12.interfaces;
import com.psk_1.psk12.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a.name FROM Book b JOIN b.authors a WHERE b.isbn = :isbn")
    List<String> getAuthorsByIsbn(@Param("isbn") String isbn);

}
