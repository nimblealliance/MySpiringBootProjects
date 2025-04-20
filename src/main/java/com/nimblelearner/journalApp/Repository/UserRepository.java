package com.nimblelearner.journalApp.Repository;

import com.nimblelearner.journalApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String username);
}
