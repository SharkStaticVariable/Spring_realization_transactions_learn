package org.example.spring_realization_transactions.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring_realization_transactions.dto.UserDto;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<UsersEntity, UserDto> {

    private final AccountsReadMapper accountsReadMapper;

    @Override
    public UserDto map(UsersEntity object) {

        return new UserDto(
                object.getUsername(),
                object.getFirstName(),
                object.getLastName(),
                object.getAge(),
                object.getPhoneNumber(),
                object.getAddress(),
                object.getMiddleName(),
                object.getEmail(),
                object.getPassword(),
                object.getRoles()
        );
    }
}
