package com.nimblelearner.DigitalLibrary.adapter;

import com.nimblelearner.DigitalLibrary.Commons.CommonAdapter;
import com.nimblelearner.DigitalLibrary.Entity.Input.UserInputEntity;
import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.UserModel;
import com.nimblelearner.DigitalLibrary.Service.UserService;
import com.nimblelearner.DigitalLibrary.mappers.input.UserInputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserAdapter implements CommonAdapter<UserInputEntity, UserModel, UUID, UserOutputEntity> {

    private final UserInputMapper inputMapper;
    private final UserService userService;

    @Autowired
    public UserAdapter(UserInputMapper inputMapper, UserService userService) {
        this.inputMapper = inputMapper;
        this.userService = userService;
    }

    @Override
    public UserModel save(UserInputEntity userInputEntity) {
        return this.userService.add(this.inputMapper.mapToModel(userInputEntity));
    }

    @Override
    public UserModel getById(UUID id) {
        return this.userService.getUserById(id);
    }

    @Override
    public boolean deleteById(UUID id) {
        return this.userService.deleteUserById(id);
    }

    @Override
    public List<UserOutputEntity> findAll() {
        return this.userService.findAllUsers();
    }
}
