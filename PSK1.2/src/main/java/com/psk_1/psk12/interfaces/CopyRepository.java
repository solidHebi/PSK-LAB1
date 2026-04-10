package com.psk_1.psk12.interfaces;
import com.psk_1.psk12.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy,Long> {
    @Query("SELECT c FROM Copy c WHERE c.book.isbn = :isbn")
    List<Copy> findCopiesByBookIsbn(@Param("isbn") String isbn);
}
