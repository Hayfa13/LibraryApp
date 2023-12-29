package com.example.LibraryApp.controller;

import com.example.LibraryApp.model.Issue;
import com.example.LibraryApp.model.Returns;
import com.example.LibraryApp.service.IssueService;
import com.example.LibraryApp.service.ReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReturnsController {

    @Autowired
    ReturnsService returnsService;

    @GetMapping("/returns")
    public ResponseEntity<List<Returns>> getAllReturns(){return ResponseEntity.ok(returnsService.findAllReturns());}//to get all issues entered

    @GetMapping("/returns/{returnsId}")//to see particular book by id
    public ResponseEntity<Returns> getReturnsById(@PathVariable("returnsId") int returnId) {
        return ResponseEntity.ok().body(returnsService.findReturnsById(returnId).orElse(null));
    }
    @PostMapping("/returns")
    public ResponseEntity<Returns> newReturns(@RequestBody Returns newReturns){
        System.out.println(newReturns.getReturnsId());
        return ResponseEntity.status(HttpStatus.CREATED).body((returnsService.saveReturns(newReturns)));
    }

    @PutMapping("/returns/{returnsId}")
    public ResponseEntity<Returns> updateReturns(@PathVariable("returnsId") int returnId, @RequestBody Returns updatedReturns) {
        return ResponseEntity.ok().body(returnsService.updateReturns(returnId, updatedReturns));
    }

    @DeleteMapping("/returns/{returnsId}") // Corrected the mapping to include the bookId
    public ResponseEntity<Object> deleteReturns(@PathVariable("returnsId") int returnsId) {
        returnsService.deleteReturns(returnsId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
