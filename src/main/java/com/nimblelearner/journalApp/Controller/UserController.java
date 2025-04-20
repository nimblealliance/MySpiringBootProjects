package com.nimblelearner.journalApp.Controller;

import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Service.JournalEntryService;
import com.nimblelearner.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private JournalEntryService journalEntryService;

    @Autowired
    public UserController(UserService userService, JournalEntryService journalEntryService) {
        this.userService = userService;
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsersInDB();
    }

    @GetMapping("/id/{myid}")
    public void getUserById(Long myid){

    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }


    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
        User userInDB = userService.findByUserName(userName);
        if (userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(userInDB , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
