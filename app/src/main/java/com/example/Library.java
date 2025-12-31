package com.example;
import java.util.ArrayList;

public class Library {
    
 public ArrayList<Book> LibraryContents = new ArrayList<Book>();

    public void addBook(Book book) {
        LibraryContents.add(book);
    }   

    public void getLibraryContents() {
        for (Book book : LibraryContents) {
            System.out.println(LibraryContents.indexOf(book) + " Title: " + book.title + ", Author: " + book.author + ", Price: " + book.price + ", Publish Date: " + book.publishDate + ", On Loan: " + book.onLoan);
        }
    }

    public void setLoanStatus(Book book, Boolean status) {
        book.onLoan = status;
    }

    public void getBooksAvailable() {
        for (Book book : LibraryContents) {
            if (!book.onLoan) {
                System.out.println(LibraryContents.indexOf(book) + " Title: " + book.title + ", Author: " + book.author + ", Price: " + book.price + ", Publish Date: " + book.publishDate + ", On Loan: " + book.onLoan);
            }
        }
    }
}
