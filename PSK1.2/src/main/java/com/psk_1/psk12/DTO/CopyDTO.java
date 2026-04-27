package com.psk_1.psk12.DTO;

public class CopyDTO {
    private String isbn;
    private int year;
    private String quality;
    private boolean in_inventory;

    CopyDTO() {}

    CopyDTO(String isbn, int year, String quality, boolean in_inventory) {
        this.isbn = isbn;
        this.year = year;
        this.quality = quality;
        this.in_inventory = in_inventory;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
    public boolean isIn_inventory() {
        return in_inventory;
    }
    public void setIn_inventory(boolean in_inventory) {
        this.in_inventory = in_inventory;
    }

}
