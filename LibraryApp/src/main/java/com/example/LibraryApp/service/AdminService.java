package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Book findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    @Override
    public Book saveBook(Book newBook) {
        String bookName = newBook.getBookName();
        String bookAuthor = newBook.getBookAuthor();

        Book existingBook = bookRepository.findByBookNameAndBookAuthor(bookName, bookAuthor);

        if (existingBook != null) {
            existingBook.setCount(existingBook.getCount() + 1);
            bookRepository.save(existingBook);
            return existingBook;
        }
        else {
            newBook.setCount(1);
            return bookRepository.save(newBook);
        }
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Optional<Book> retrievedBook = bookRepository.findById(bookId);

        if (retrievedBook.isEmpty()) {
            throw new RuntimeException("Book Not Found");//if book that needs to be updated not present throw error
        }
        Book existingBook = retrievedBook.get();
        existingBook.setBookName(book.getBookName());
        existingBook.setBookAuthor(book.getBookAuthor());

        bookRepository.save(existingBook);
        return existingBook;
    }

    @Override
    public Book deleteBook(int bookId) {
        Optional<Book> retrievedBook = bookRepository.findById(bookId);

        if (retrievedBook.isEmpty()) {
            throw new RuntimeException("Book Not Found");
        }

        bookRepository.deleteById(bookId);
        return retrievedBook.orElse(null);
    }
}










