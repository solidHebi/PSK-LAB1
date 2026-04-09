package com.psk_1.psk12.interfaces;
import com.psk_1.psk12.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String>{
}
