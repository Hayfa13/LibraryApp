package com.example.LibraryApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Returns")
public class Returns {

    @Id
    @Column(name = "ReturnID")
    private Integer returnsId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book bookId;

    @Column(name = "ReturnDate")
    private String returnsDate;

    @Column(name = "ReturnTime")
    private String returnsTime;

}