package org.example.spring_realization_transactions.mapper;

import org.example.spring_realization_transactions.dto.UserDto;
import org.example.spring_realization_transactions.entity.UsersEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UsersEntity toUserEntity(UserDto userDto);
}
