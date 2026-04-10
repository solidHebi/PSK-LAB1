package com.psk_1.psk12.DTO;

import java.util.List;

public class BookDTO
{
    private String isbn;
    private String title;
    private List<String> authors;
    private String genre;
    private int year;

    public BookDTO(String isbn, String title, List<String> authors, String genre, int year) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.year = year;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public String getGenre() {
        return genre;
    }
    public int getYear() {
        return year;
    }
}
