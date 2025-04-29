package com.nimblelearner.journalApp.Service;

import com.nimblelearner.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){

        Assertions.assertNotNull(userRepository.findByUserName("ramenon"));

    }

//    @Test
//    public void testFindByParticularUserName(){
//        Assertions.assertEquals(user , userRepository.findByUserName("boo"));
//
//    }
}
