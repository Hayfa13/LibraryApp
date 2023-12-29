package com.example.LibraryApp.repository;

import com.example.LibraryApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUserName(String userName);
}
