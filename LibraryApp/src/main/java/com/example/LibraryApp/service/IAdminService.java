package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Book;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

    List<Book> findAllBooks();

    Optional<Book> findBookById(int bookId);

    Book findByBookName(String bookName);

    Book saveBook(Book newBook);

    Book updateBook(int bookId , Book book);

    Book deleteBook(int bookId);
}
