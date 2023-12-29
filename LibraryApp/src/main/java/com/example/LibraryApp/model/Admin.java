package com.example.LibraryApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Admin")
public class Admin {

        @Id
        @Column(name = "ID")
        private Integer adminId;

        @Column(name = "Username")
        private String userName;

        @Column(name = "Password")
        private String password;

        @Column(name = "Role")
        private String role;
    }