package com.example.LibraryApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Issue")
public class Issue {

    @Id
    @Column(name = "IssueID")
    private Integer issueId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User userID;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book bookId;

    @Column(name = "IssueDate")
    private String issueDate;

    @Column(name = "IssueTime")
    private String issueTime;

}
