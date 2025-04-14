package com.nimblelearner.DigitalLibrary.Service;

import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.UserModel;
import com.nimblelearner.DigitalLibrary.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel add(UserModel userModel){
        return this.userRepository.save(userModel);
    }

    public UserModel getUserById(UUID id){
        return this.userRepository.findById(id);
    }

    public boolean deleteUserById(UUID id){

        if( getUserById(id)!= null){
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<UserOutputEntity> findAllUsers(){
        return this.userRepository.findAllUsers();
    }

}
