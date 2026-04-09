package com.psk_1.psk12.interfaces;
import com.psk_1.psk12.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
