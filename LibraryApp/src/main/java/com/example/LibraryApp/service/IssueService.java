package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.Issue;
import com.example.LibraryApp.model.Returns;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.IssueRepository;
import com.example.LibraryApp.repository.ReturnsRepository;
import com.example.LibraryApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService implements IIssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReturnsRepository returnsRepository;

    @Override
    public List<Issue> findAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public Optional<Issue> findIssueById(int issueId) {
        return issueRepository.findById(issueId);
    }

    @Override
    public Issue saveIssue(Issue newIssue) {
        // Check if the book and user exist
        boolean isBookAvailable = bookRepository.existsById(newIssue.getBookId().getBookId());
        boolean isUserAvailable = userRepository.existsById(newIssue.getUserID().getUserId());

        if (!isBookAvailable || !isUserAvailable) {
            throw new RuntimeException("Book or User not available");
        }

        // Check if the book is already issued and not returned
        boolean isBookAlreadyIssued = issueRepository.existsByIssueDate(newIssue.getIssueDate());
        boolean isBookAlreadyReturned = returnsRepository.existsByReturnsDate(newIssue.getIssueDate());


        if (isBookAlreadyIssued && !isBookAlreadyReturned) {
            throw new RuntimeException("Book is already issued and not returned");
        }

        Book book = bookRepository.findById(newIssue.getBookId().getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Decrease the book count by 1
        int currentCount = book.getCount();
        if (currentCount > 0) {
            book.setCount(currentCount - 1);
        } else {
            // If count is 1, remove the book
            bookRepository.delete(book);
        }

        // Save the updated book entity
        bookRepository.save(book);
        // Save the issue if all conditions are met
        return issueRepository.save(newIssue);
    }

    @Override
    public Issue updateIssue(int issueId, Issue issue) {
        Optional<Issue> retrievedIssue = issueRepository.findById(issueId);

        if (retrievedIssue.isEmpty()) {
            throw new RuntimeException("Issue Not Found");
        }

        issueRepository.save(issue);
        return issueRepository.findById(issueId).orElse(null);
    }

    @Override
    public Issue deleteIssue(int issueId) {
        Optional<Issue> retrievedIssue = issueRepository.findById(issueId);

        if (retrievedIssue.isEmpty()) {
            throw new RuntimeException("Issue Not Found");
        }

        issueRepository.deleteById(issueId);
        return retrievedIssue.orElse(null);
    }
}
