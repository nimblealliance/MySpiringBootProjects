package com.nimblelearner.journalApp.Controller;

import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<String> ("ping",HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return userService.saveNewUser(user);
    }
}
