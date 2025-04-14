package com.nimblelearner.DigitalLibrary.Controller;

import com.nimblelearner.DigitalLibrary.Commons.CommonAdapter;
import com.nimblelearner.DigitalLibrary.Entity.Input.UserInputEntity;
import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CommonAdapter<UserInputEntity, UserModel, UUID, UserOutputEntity> userAdapter;

    @Autowired
    public UserController(CommonAdapter<UserInputEntity, UserModel, UUID, UserOutputEntity> userAdapter) {
        this.userAdapter = userAdapter;
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserInputEntity user){
        return new ResponseEntity<UserModel>(this.userAdapter.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserById(@RequestParam UUID id){
        if(this.userAdapter.getById(id)!= null){
            return new ResponseEntity<UserModel>(this.userAdapter.getById(id),HttpStatus.OK);

        }
        return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/del-user")
    public ResponseEntity<?> deleteById(@RequestParam UUID id){
        if (this.userAdapter.deleteById(id)) {
            return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUserById(@RequestParam UUID id , @RequestBody UserInputEntity user){
        if (this.userAdapter.getById(id)!=null) {

            return new ResponseEntity<UserModel>(this.userAdapter.save(user),HttpStatus.OK );
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<List<UserOutputEntity>>( this.userAdapter.findAll(),HttpStatus.OK );
    }


}
