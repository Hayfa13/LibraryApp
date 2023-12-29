package com.example.LibraryApp.controller;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.User;
import com.example.LibraryApp.service.AdminService;
import com.example.LibraryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class StudentController {

    @Autowired
    UserService userService;
    @GetMapping("/student")
    public ResponseEntity<List<User>> getAllUsers(){return ResponseEntity.ok(userService.findAllUsers());}

    @GetMapping("/student/{studentId}")//to see particular book by id
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {
        return ResponseEntity.ok().body(userService.findUserById(userId).orElse(null));
    }

}

