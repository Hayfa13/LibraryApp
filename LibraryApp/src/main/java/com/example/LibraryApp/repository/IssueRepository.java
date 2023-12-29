package com.example.LibraryApp.repository;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    List<Issue> findByIssueDate(String issueDate);
    boolean existsByIssueDate(String issueDate);
    boolean existsByBookId (Book book);

}
