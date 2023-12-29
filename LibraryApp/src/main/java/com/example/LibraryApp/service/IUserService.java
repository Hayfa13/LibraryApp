package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAllUsers();

    Optional<User> findUserById(int userId);

}
