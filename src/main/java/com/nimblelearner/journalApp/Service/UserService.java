package com.nimblelearner.journalApp.Service;

import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsersInDB(){
        return userRepository.findAll();

    }

    public User saveUser(User user){
        return userRepository.save(user);

    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);

    }

    public Boolean deleteUserById(Long id){
        User userById = getUserById(id);
        if (userById != null){
            userRepository.deleteById(id);
            return true;
        }
        return false;

    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }




}
