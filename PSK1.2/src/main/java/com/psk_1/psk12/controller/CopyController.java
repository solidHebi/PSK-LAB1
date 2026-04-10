package com.psk_1.psk12.controller;


import com.psk_1.psk12.model.Copy;
import com.psk_1.psk12.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CopyController {
    private CopyService copyService;
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }
    @GetMapping("copies/{isbn}")
    public List<Copy> getCopies(@PathVariable("isbn") String isbn) {
        return copyService.getCopiesOfBook(isbn);
    }
}
