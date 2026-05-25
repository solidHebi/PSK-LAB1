package com.psk_1.psk12.controller;

import com.psk_1.psk12.DTO.BookDTO;
import com.psk_1.psk12.service.BookService;
import com.psk_1.psk12.service.LongCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // React dev server
public class BookController {
    private final BookService bookService;
    private final LongCalculationService longCalculationService;

    public BookController(BookService bookService, LongCalculationService longCalculationService) {
        this.bookService = bookService;
        this.longCalculationService = longCalculationService;
    }

    @GetMapping("/AllBooksWithAuthors")
    public List<BookDTO> getAllBooksWithAuthors(){
        longCalculationService.performLongCalculation();
        return bookService.getBooksAndAuthors();
    }
}
