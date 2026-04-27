package com.psk_1.psk12.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "copy")
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean in_inventory;
    @Column
    private int year;
    @Column
    private String quality;


    @ManyToOne
    @JoinColumn(name = "isbn")
    private Book book;

    public Copy() {}
    public Copy(Long id, String name, boolean in_inventory, int year, String quality, Book book) {
        this.id = id;
        this.in_inventory = in_inventory;
        this.year = year;
        this.quality = quality;
        this.book = book;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return book.getTitle();
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;

    }
    public boolean isInInventory() {
        return in_inventory;
    }
    public void setInInventory(boolean in_inventory) {
        this.in_inventory = in_inventory;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    public Book getBook() {
        return book;
    }
}
