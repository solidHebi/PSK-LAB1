package com.psk_1.psk12.controller;

import com.psk_1.psk12.model.Author;
import com.psk_1.psk12.repository.AuthorRepositoryMemory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // React dev server
public class AuthorController {
    AuthorRepositoryMemory authorRepositoryMemory = new AuthorRepositoryMemory();

    @GetMapping("/hello")
    public String hello(){
        return "ようこそわたしのソウル－";
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorRepositoryMemory.findAll();
    }
}