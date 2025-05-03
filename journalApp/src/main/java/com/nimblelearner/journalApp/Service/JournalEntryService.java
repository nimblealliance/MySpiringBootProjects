package com.nimblelearner.journalApp.Service;

import com.nimblelearner.journalApp.Entity.JournalEntry;
import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
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

    public List<JournalEntry> getAllEntriesOfUserInJournal(String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> allEntriesInJournal  = user.getJournalEntries();;
        return allEntriesInJournal;
    }

    public JournalEntry createEntryInJournal(JournalEntry journalEntry){
         journalEntryRepository.save(journalEntry);
         return journalEntry;
    }

    @Transactional
    public void createEntryInJournal(JournalEntry journalEntry, String userName   ){
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUserEntry(user);
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

    @Transactional
    public Boolean deleteJournalEntryByUser(String userName , Long id){
        User user = userService.findByUserName(userName);
        if (user != null){
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUserEntry(user);
                journalEntryRepository.deleteById(id);
                return true;
            }
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

    public JournalEntry updateJournalEntryByUser(String userName , Long id , JournalEntry newEntry){
        JournalEntry oldEntry = getJournalEntryById(id);
        User user = userService.findByUserName(userName);

        if(oldEntry != null && user.getUserName().equals(userName)){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryRepository.save(oldEntry);
        }
        return oldEntry;
    }

}
