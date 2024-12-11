package org.example.spring_realization_transactions.dto;

import lombok.Value;
import org.example.spring_realization_transactions.entity.RolesEntity;

@Value
public class UserReadDto {
    Integer id;
    String username;
    String firstName;
    String lastName;
    int age;
    String phoneNumber;
    String address;
    String middleName;
    String email;
    String password;
    RolesEntity roles;
}