package com.example.LibraryApp.repository;

import com.example.LibraryApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByBookName(String bookName);
    Book findByBookNameAndBookAuthor(String bookName,String bookAuthor);
}
