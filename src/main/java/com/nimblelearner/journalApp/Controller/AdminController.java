package com.nimblelearner.journalApp.Controller;

import com.nimblelearner.journalApp.Entity.JournalEntry;
import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Service.JournalEntryService;
import com.nimblelearner.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")


public class AdminController {


    private UserService userService;
    private JournalEntryService journalEntryService;

    @Autowired
    public AdminController(UserService userService, JournalEntryService journalEntryService) {
        this.userService = userService;
        this.journalEntryService = journalEntryService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){

        List<User> allUsersInDB = userService.getAllUsersInDB();
        if (allUsersInDB != null && !allUsersInDB.isEmpty()){
            return new ResponseEntity<>(allUsersInDB, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all-entries")
    public ResponseEntity<?> getAllEntries(){

        List<JournalEntry> allEntriesInJournal = journalEntryService.getAllEntriesInJournal();
        if (allEntriesInJournal != null && !allEntriesInJournal.isEmpty()){
            return new ResponseEntity<>(allEntriesInJournal, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public User createAdminUser(@RequestBody User user){
        return userService.saveAdmin(user);
    }
}
