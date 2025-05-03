package com.nimblelearner.journalApp.Controller;

import com.nimblelearner.journalApp.Entity.JournalEntry;
import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Service.JournalEntryService;
import com.nimblelearner.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    private final JournalEntryService journalEntryService;
    private final UserService userService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUserName(name);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()){
            JournalEntry entryById = journalEntryService.getJournalEntryById(id);
            return new ResponseEntity<JournalEntry>(entryById, HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        List<JournalEntry> allEntriesOfUserInJournal = journalEntryService.getAllEntriesOfUserInJournal(name);
        if (allEntriesOfUserInJournal != null && !allEntriesOfUserInJournal.isEmpty()){
            return new ResponseEntity<>(allEntriesOfUserInJournal,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntryByUser(@RequestBody JournalEntry myEntry ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (myEntry != null){
            journalEntryService.createEntryInJournal(myEntry,name);
            return new ResponseEntity<JournalEntry>(HttpStatus.CREATED);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUserName(name);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()){
            JournalEntry updatedJournalEntryById = journalEntryService.updateJournalEntryById(id, newEntry);
            return new ResponseEntity<>(updatedJournalEntryById,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalEntriesByUser (@PathVariable Long id ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(journalEntryService.deleteJournalEntryByUser(name , id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
