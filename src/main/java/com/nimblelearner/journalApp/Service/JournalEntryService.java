package com.nimblelearner.journalApp.Service;

import com.nimblelearner.journalApp.Entity.JournalEntry;
import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {


    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    @Autowired
    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    public List<JournalEntry> getAllEntriesInJournal(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry createEntryInJournal(JournalEntry journalEntry){
         journalEntryRepository.save(journalEntry);
         return journalEntry;
    }

    public void createEntryInJournal(JournalEntry journalEntry, String userName){

        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public JournalEntry getJournalEntryById(Long id){
        return journalEntryRepository.findById(id).orElse(null);
    }

    public Boolean deleteJournalEntryById(Long id){
        JournalEntry existingEntry = getJournalEntryById(id);
        if (existingEntry != null){
            journalEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public JournalEntry updateJournalEntryById( Long id,  JournalEntry newEntry){
        JournalEntry oldEntry = getJournalEntryById(id);
        if (oldEntry !=null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryRepository.save(oldEntry);
        }
        return oldEntry;

    }

}
