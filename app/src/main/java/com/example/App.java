package com.example;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;



public class App {

    static Library library = new Library();   
    static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) throws Exception {
        // loadBooks(library);
        loadJsonBooks(library);

        runMenu();
        scanner.close();
    }
    
    // public static void loadBooks(Library library) {    
    //     Book b1 = new Book("1984", "George Orwell", "9.99", "1949-06-08", false, library);
    //     Book b2 = new Book("The Hobbit", "J.R.R. Tolkien", "12.50", "1937-09-21", true, library);
    //     Book b3 = new Book("Dune", "Frank Herbert", "15.00", "1965-08-01", false, library);
    // }

    public static void loadJsonBooks(Library library) {
        try {
            ObjectMapper mapper = new ObjectMapper(); //Jackson
            File jsonFile = new File("books.json");
            // The JSON is an array ([...] in books.json file) of Book objects. I want a Java array of Book - Book[].class - I want multiple books.
            Book[] books = mapper.readValue(jsonFile, Book[].class);

            for (Book book : books) {
            library.addBook(book);
            }
        }   catch (Exception e) {
            throw new RuntimeException("Failed to load books.json", e);
        }
    }

    public static void saveJsonBooks(Library library) {
    try {
        ObjectMapper mapper = new ObjectMapper();

        // Convert your LibraryContents (ArrayList<Book>) to JSON file
        mapper.writerWithDefaultPrettyPrinter() // makes it nicely formatted
              .writeValue(new File("test.json"), library.LibraryContents);

        System.out.println("Library saved to books.json successfully.");

    } catch (Exception e) {
        throw new RuntimeException("Failed to save books.json", e);
    }
}

    
    public static void addBook(String title, String author, String price, String publishDate, Boolean onLoan, Library library) {
        Book newBook = new Book(title, author, price, publishDate, onLoan, library);
        library.addBook(newBook);
    }

    public static void runMenu(){
        boolean running = true;
        while (running){
            System.out.println("----------------------");
            System.out.println("Welcome to the Library");
            System.out.println("----------------------");
            System.out.println("What would you like to do");
            System.out.println("0. Add a book to the library");   
            System.out.println("1. See all books in the library");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit"); 
            System.out.println("----------------------");
            System.out.println("");
            String choice = scanner.nextLine();
            
            if (choice.equals("0")){
                addBook();
            }
            if (choice.equals("1")){
                listBooks(library);
            }
            if (choice.equals("2")){
                borrowBook();
            }
            if (choice.equals("3")){
                returnBook();
            }
            if (choice.equals("4")){
                saveJsonBooks(library);
                System.exit(0);
            }

            System.out.println("");
        }
    }

    public static void addBook(){
        System.out.println("Enter Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Author:");
        String author = scanner.nextLine();
        System.out.println("Enter price:");
        String price = scanner.nextLine();
        System.out.println("Date Published: (YYYY-MM-DD):");
        String publishDate = scanner.nextLine();
        Boolean onLoan = false;
        addBook(title, author, price, publishDate, onLoan, library);
   
    }

    public static void listBooks(Library library) {
        library.getLibraryContents();
    }

     private static void borrowBook() {
       library.getBooksAvailable();
         System.out.println("What book number would you like to borrow?");
            int choice = Integer.parseInt(scanner.nextLine());
            Book bookToBorrow = library.LibraryContents.get(choice);
            library.setLoanStatus(bookToBorrow, true);
    }

    private static void returnBook() {
        System.out.println("Enter the title of the book you would like to return:");
        String title = scanner.nextLine();
        for (Book book : library.LibraryContents) {
            if (book.title.equalsIgnoreCase(title)) {
                library.setLoanStatus(book, false);
                System.out.println("You have returned: " + book.title);
                return;
            }
        }
    }
}