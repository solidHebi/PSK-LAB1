package com.psk_1.psk12.service;

import com.psk_1.psk12.DTO.CopyDTO;
import com.psk_1.psk12.interfaces.BookRepository;
import com.psk_1.psk12.interfaces.CopyRepository;
import com.psk_1.psk12.model.Book;
import com.psk_1.psk12.model.Copy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {
    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;
    public CopyService(CopyRepository copyRepository, BookRepository bookRepository) {
        this.copyRepository = copyRepository;
        this.bookRepository = bookRepository;
    }

    public List<Copy> getCopiesOfBook(String isbn) {
        return copyRepository.findCopiesByBookIsbn(isbn);
    }
    public void createCopy(CopyDTO dto) {

        Book book = bookRepository.findById(dto.getIsbn()).orElseThrow();

        Copy copy = new Copy();
        copy.setBook(book);
        copy.setYear(dto.getYear());
        copy.setQuality(dto.getQuality());
        copy.setInInventory(dto.isIn_inventory());

        copyRepository.save(copy);
    }

}
