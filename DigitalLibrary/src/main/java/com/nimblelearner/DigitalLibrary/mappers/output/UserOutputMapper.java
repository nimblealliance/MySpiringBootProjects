package com.nimblelearner.DigitalLibrary.mappers.output;

import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserOutputMapper {

    public UserModel mapToModel(UserOutputEntity userOutputEntity){

        return UserModel.builder()
                .id(userOutputEntity.getId())
                .firstName(userOutputEntity.getFirstName())
                .lastName(userOutputEntity.getLastName())
                .dob(userOutputEntity.getDob())
                .email(userOutputEntity.getEmail())
                .phoneNumber(userOutputEntity.getPhoneNumber())
                .build();
    }

    public UserOutputEntity mapFromModel(UserModel userModel){
        return UserOutputEntity.builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .dob(userModel.getDob())
                .email(userModel.getEmail())
                .phoneNumber(userModel.getPhoneNumber())
                .build();
    }

}
