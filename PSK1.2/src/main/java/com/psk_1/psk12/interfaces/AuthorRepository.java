package com.psk_1.psk12.interfaces;
import com.psk_1.psk12.model.Author;
import java.util.ArrayList;
import java.util.List;
public interface AuthorRepository {

    public List<Author> findAll();

    public Author findById(long id);

    public void addAuthor(Author author);

    public void deleteAuthor(long id);
}
