package com.example.LibraryApp.repository;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.Issue;
import com.example.LibraryApp.model.Returns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnsRepository extends JpaRepository<Returns,Integer> {
    List<Returns> findByReturnsDate(String returnsDate);
    boolean existsByReturnsDate(String returnsDate);

}
