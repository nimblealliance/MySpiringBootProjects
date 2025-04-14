package com.nimblelearner.DigitalLibrary.Repository;

import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.UserModel;
import com.nimblelearner.DigitalLibrary.Repository.jpa.UserJPARepository;
import com.nimblelearner.DigitalLibrary.mappers.output.UserOutputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository {

    private final UserJPARepository userJPARepository;
    private final UserOutputMapper userOutputMapper;

    @Autowired
    public UserRepository(UserJPARepository userJPARepository, UserOutputMapper userOutputMapper) {
        this.userJPARepository = userJPARepository;
        this.userOutputMapper = userOutputMapper;
    }

    public List<UserOutputEntity> findAllUsers(){
        return this.userJPARepository.findAll();
    }

    public UserModel save(UserModel user){
        UserOutputEntity outputEntity = this.userOutputMapper.mapFromModel(user);
        UserOutputEntity savedOutputEntity = userJPARepository.save(outputEntity);
        return this.userOutputMapper.mapToModel(savedOutputEntity);
    }

    public UserModel findById(UUID id){

        Optional<UserOutputEntity> optionalUserOutputEntity = this.userJPARepository.findById(id);
        return optionalUserOutputEntity.map(this.userOutputMapper::mapToModel).orElse(null);
    }

    public void deleteById(UUID id){
        this.userJPARepository.deleteById(id);
    }


}
