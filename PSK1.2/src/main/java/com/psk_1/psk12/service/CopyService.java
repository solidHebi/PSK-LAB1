package com.psk_1.psk12.service;

import com.psk_1.psk12.interfaces.CopyRepository;
import com.psk_1.psk12.model.Copy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {
    private final CopyRepository copyRepository;
    public CopyService(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    public List<Copy> getCopiesOfBook(String isbn) {
        return copyRepository.findCopiesByBookIsbn(isbn);
    }

}
