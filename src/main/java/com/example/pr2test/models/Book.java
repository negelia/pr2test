package com.example.pr2test.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iDBook;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max = 50, message = "Введите от 2х до 50ти символов")
    private String title, author;

    @NotNull(message = "Введите дату")
    private String date;

    private Boolean ordered;

    @Digits(integer = 1000, fraction = 0, message = "Введите не более 1000 знаков")
    @Positive(message = "В книге не может содержатся 0 и меньше страниц")
    private int pages;

    public Book(String title, String author, String date, Boolean ordered, int pages) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.ordered = ordered;
        this.pages = pages;
    }

    public Book() {

    }

    public Long getiDBook() {
        return iDBook;
    }

    public void setiDBook(Long iDBook) {
        this.iDBook = iDBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
