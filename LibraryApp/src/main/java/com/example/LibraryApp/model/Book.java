package com.example.LibraryApp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "ID")
    private Integer bookId;

    @Column(name = "Name")
    private String bookName;

    @Column(name = "Author")
    private String bookAuthor;

    @Column(name = "Count")
    private  transient Integer count=1;
}
