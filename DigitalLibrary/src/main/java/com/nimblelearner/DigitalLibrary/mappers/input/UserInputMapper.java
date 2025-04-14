package com.nimblelearner.DigitalLibrary.mappers.input;

import com.nimblelearner.DigitalLibrary.Entity.Input.UserInputEntity;
import com.nimblelearner.DigitalLibrary.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserInputMapper {

    public UserModel mapToModel(UserInputEntity inputEntity){
        return UserModel.builder()
                .id(inputEntity.getId())
                .dob(inputEntity.getDob())
                .phoneNumber(inputEntity.getPhoneNumber())
                .email(inputEntity.getEmail())
                .firstName(inputEntity.getFirstName())
                .lastName(inputEntity.getLastName())
                .build();
    }
}
