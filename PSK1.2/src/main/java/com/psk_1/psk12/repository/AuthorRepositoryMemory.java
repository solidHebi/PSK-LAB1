package com.psk_1.psk12.repository;

import com.psk_1.psk12.interfaces.AuthorRepository;
import com.psk_1.psk12.model.Author;

import java.util.ArrayList;
import java.util.*;

public class AuthorRepositoryMemory implements AuthorRepository {

    private List<Author> authors =  new ArrayList<>();
    public AuthorRepositoryMemory() {
        authors.add(new Author("Joshua Bloch"));
        authors.add(new Author("Robert Martin"));
        authors.add(new Author("Martin Fowler"));
    }
    public List<Author> findAll(){
        return authors;
    }
    public Author findById(long id){
        for(Author author : authors){
            if(author.getId() == id){
                return author;
            }
        }
        return null;
    }
    public void addAuthor(Author author){
        authors.add(author);
    }

    public void deleteAuthor(long id){
        authors.removeIf(a -> a.getId() == id);
    }
}
