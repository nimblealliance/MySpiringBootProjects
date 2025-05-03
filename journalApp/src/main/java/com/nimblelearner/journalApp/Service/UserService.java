package com.nimblelearner.journalApp.Service;

import com.nimblelearner.journalApp.Entity.User;
import com.nimblelearner.journalApp.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsersInDB(){
        return userRepository.findAll();

    }

    public User saveNewUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);

    }

    public User saveUserEntry(User user){
        return userRepository.save(user);
    }

    public User saveAdmin(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
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

    @Transactional
    public void deleteByUserName(String username){
        userRepository.deleteByUserName(username);
    }

}
