package com.psk_1.psk12.service;

import com.psk_1.psk12.DTO.BookDTO;
import com.psk_1.psk12.interfaces.AuthorRepository;
import com.psk_1.psk12.interfaces.BookRepository;
import com.psk_1.psk12.model.Author;
import com.psk_1.psk12.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getBooksAndAuthors() {
        List<Book> books = bookRepository.findAllWithAuthors();
        return books.stream()
            .map(b -> new BookDTO(
                    b.getIsbn(),
                    b.getTitle(),
                    b.getAuthors().stream().map(Author::getName).toList(),
                    b.getGenre(),
                    b.getYear()
            ))
            .toList();
    }
}
