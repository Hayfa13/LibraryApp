package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.Issue;
import com.example.LibraryApp.model.Returns;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

public interface IIssueService {

    List<Issue> findAllIssues();

    Optional<Issue> findIssueById(int issueId);

    Issue saveIssue(Issue newIssue);

    Issue updateIssue(int issueId , Issue issue);

    Issue deleteIssue(int issueId);

}
