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
@RequestMapping("/journal")
public class JournalEntryController {


    private final JournalEntryService journalEntryService;
    private final UserService userService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> allEntriesInJournal = journalEntryService.getAllEntriesInJournal();
        if (allEntriesInJournal != null && !allEntriesInJournal.isEmpty()){
            return new ResponseEntity<>(allEntriesInJournal,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> allEntriesInJournal = user.getJournalEntries();
        if (allEntriesInJournal != null && !allEntriesInJournal.isEmpty()){
            return new ResponseEntity<>(allEntriesInJournal,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createJournalEntryByUser(@RequestBody JournalEntry myEntry , @PathVariable String userName){

        if (myEntry != null){

            journalEntryService.createEntryInJournal(myEntry,userName);
            return new ResponseEntity<JournalEntry>(HttpStatus.CREATED);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long myId){
        JournalEntry entryById = journalEntryService.getJournalEntryById(myId);
        if(entryById != null){
            return new ResponseEntity<JournalEntry>(entryById, HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable Long myId){
        if (journalEntryService.deleteJournalEntryById(myId)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry newEntry){
        JournalEntry updatedJournalEntryById = journalEntryService.updateJournalEntryById(myId, newEntry);
        if(updatedJournalEntryById != null){
            return new ResponseEntity<>(updatedJournalEntryById,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
