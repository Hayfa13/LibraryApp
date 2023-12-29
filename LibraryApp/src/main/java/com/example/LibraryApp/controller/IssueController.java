package com.example.LibraryApp.controller;

import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.model.Issue;
import com.example.LibraryApp.service.AdminService;
import com.example.LibraryApp.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class IssueController {

    @Autowired
    IssueService issueService;

    @GetMapping("/issues")
    public ResponseEntity<List<Issue>> getAllIssues(){return ResponseEntity.ok(issueService.findAllIssues());}//to get all issues entered

    @GetMapping("/issue/{issueId}")//to see particular book by id
    public ResponseEntity<Issue> getIssueById(@PathVariable("issueId") int issueId) {
        return ResponseEntity.ok().body(issueService.findIssueById(issueId).orElse(null));
    }
    @PostMapping("/issues")
    public ResponseEntity<Issue> saveIssue(@RequestBody Issue newIssue){
        System.out.println(newIssue.getIssueId());
        return ResponseEntity.status(HttpStatus.CREATED).body((issueService.saveIssue(newIssue)));
    }

    @PutMapping("/issues/{issueId}")
    public ResponseEntity<Issue> updateIssue(@PathVariable("issueId") int issueId, @RequestBody Issue updatedIssue) {
        return ResponseEntity.ok().body(issueService.updateIssue(issueId, updatedIssue));
    }

    @DeleteMapping("/issues/{issueId}") // Corrected the mapping to include the bookId
    public ResponseEntity<Object> deleteIssue(@PathVariable("issueId") int issueId) {
        issueService.deleteIssue(issueId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
