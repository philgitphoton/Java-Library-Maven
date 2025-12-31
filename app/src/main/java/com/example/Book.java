package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book {
    
    public String title;
    public String author;
    public String price;
    public String publishDate;
    public Boolean onLoan;
    
    @JsonIgnore // this is a jackson thing - without this annotation when we try to serialize Library -> Book -> Library -> Book ...loop
    public Library library;
    
    public Book(){}

    public Book(String title, String author, String price, String publishDate, Boolean onLoan, Library library) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishDate = publishDate;
        this.onLoan = onLoan; 
        this.library = library;
        // library.addBook(this);
    }

      public String getTitle(Book book) {
        return this.title;
    }   
}