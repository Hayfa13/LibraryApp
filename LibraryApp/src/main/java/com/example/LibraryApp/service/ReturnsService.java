package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Book;
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
public class ReturnsService implements IReturnsService {
    @Autowired
    private ReturnsRepository returnsRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Returns> findAllReturns() {
        return returnsRepository.findAll();
    }

    @Override
    public Optional<Returns> findReturnsById(int returnsId) {
        return returnsRepository.findById(returnsId);
    }

    @Override
    public Returns saveReturns(Returns newReturns) {
        boolean isBookAvailable = bookRepository.existsById(newReturns.getBookId().getBookId());
        boolean isUserAvailable = userRepository.existsById(newReturns.getUserId().getUserId());

        if (!isBookAvailable || !isUserAvailable) {
            throw new RuntimeException("Book or User not available");
        }

        // Check if the book is already issued and not returned
        boolean isBookIssued= issueRepository.existsByBookId(newReturns.getBookId());

        if (!isBookIssued) {
            throw new RuntimeException("Book is not yet issued");
        }

        Book book = bookRepository.findById(newReturns.getBookId().getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

            book.setCount(book.getCount() + 1);

            // If count is 1, remove the book
            bookRepository.save(book);

        return returnsRepository.save(newReturns);

    }

    @Override
    public Returns updateReturns(int returnsId, Returns returns) {
        Optional<Returns> retrievedReturns = returnsRepository.findById(returnsId);

        if (retrievedReturns.isEmpty()) {
            throw new RuntimeException("Return Not Found");
        }

        returnsRepository.save(returns);
        return returnsRepository.findById(returnsId).orElse(null);

    }
    @Override
    public Returns deleteReturns(int returnsId) {
        Optional<Returns> retrievedReturns= returnsRepository.findById(returnsId);

        if (retrievedReturns.isEmpty()) {
            throw new RuntimeException("Return Not Found");
        }

        returnsRepository.deleteById(returnsId);
        return retrievedReturns.orElse(null);
    }
}


