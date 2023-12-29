package com.example.LibraryApp.controller;


import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    AdminService adminService;
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){return ResponseEntity.ok(adminService.findAllBooks());}

    @GetMapping("/book/{id}")//to see particular book by id
    public ResponseEntity<Book> getBookById(@PathVariable("id") int bookId) {
        return ResponseEntity.ok().body(adminService.findBookById(bookId).orElse(null));
    }
    @GetMapping("/book/search")
    public ResponseEntity<?> userDetails(@RequestParam("bookName") String bookName) {
        Book book = adminService.findByBookName(bookName);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        return ResponseEntity.ok().body(book);
    }
}
