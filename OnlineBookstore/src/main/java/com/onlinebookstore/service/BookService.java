package com.onlinebookstore.service;

import com.onlinebookstore.dao.BookDAO;
import com.onlinebookstore.model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void addSampleBook() {
        bookDAO.addBook(new Book(0, "Clean Code", "Robert C. Martin", 45.99));
    }

    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getAuthor() + " | $" + book.getPrice());
        }
    }
}
