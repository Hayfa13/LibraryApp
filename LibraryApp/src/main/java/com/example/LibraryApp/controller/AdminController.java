package com.example.LibraryApp.controller;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletResponse response){
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);

            Cookie userCookie = new Cookie("user", username);
            userCookie.setMaxAge(86400);
            userCookie.setPath("/api/v1/admin");
            response.addCookie(userCookie);
            return ResponseEntity.ok().body("Login successful");

    }

    private boolean authenticateAdmin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName()) && "admin".equals(cookie.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(HttpServletRequest request) {
        if (authenticateAdmin(request)) {
            return ResponseEntity.ok(adminService.findAllBooks());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") int bookId) {
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

    @PostMapping(value = "/books",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> saveBook(@RequestBody Book newBook){
        System.out.println(newBook.getBookName());
        return ResponseEntity.status(HttpStatus.CREATED).body((adminService.saveBook(newBook)));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") int bookId, @RequestBody Book updatedBook) {
        return ResponseEntity.ok().body(adminService.updateBook(bookId, updatedBook));
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Object> deleteBook(@PathVariable("bookId") int bookId) {
        adminService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}