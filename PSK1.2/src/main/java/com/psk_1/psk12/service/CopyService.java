package com.psk_1.psk12.service;

import com.psk_1.psk12.DTO.CopyDTO;
import com.psk_1.psk12.Exceptions.CopyModifiedException;
import com.psk_1.psk12.interfaces.BookRepository;
import com.psk_1.psk12.interfaces.CopyRepository;
import com.psk_1.psk12.model.Book;
import com.psk_1.psk12.model.Copy;
import jakarta.transaction.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
    @Transactional
    public List<Copy> getCopiesOfBook(String isbn) {
        return copyRepository.findCopiesByBookIsbn(isbn);
    }

    @Transactional
    public void createCopy(CopyDTO dto) {

        Book book = bookRepository.findById(dto.getIsbn()).orElseThrow();

        Copy copy = new Copy();
        copy.setBook(book);
        copy.setYear(dto.getYear());
        copy.setQuality(dto.getQuality());
        copy.setInInventory(dto.isIn_inventory());

        copyRepository.save(copy);
    }

    @Transactional
    public void updateCopy(Long copyId, CopyDTO dto) {

        try {

            Copy copy = copyRepository.findById(copyId)
                    .orElseThrow(() -> new RuntimeException("Copy not found"));

            Book book = bookRepository.findById(dto.getIsbn())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            copy.setBook(book);
            copy.setYear(dto.getYear());
            copy.setQuality(dto.getQuality());
            copy.setInInventory(dto.isIn_inventory());

            Thread.sleep(3000);

            copyRepository.saveAndFlush(copy);

        } catch (ObjectOptimisticLockingFailureException e) {
            throw new CopyModifiedException();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
