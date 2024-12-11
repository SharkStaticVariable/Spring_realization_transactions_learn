package org.example.spring_realization_transactions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_realization_transactions.entity.RolesEntity;

@Data
@NoArgsConstructor
@Builder
public class UserDto {
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

    public UserDto(String username, String firstName, String lastName, int age, String phoneNumber, String address, String middleName, String email, String password,  RolesEntity roles) {
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public RolesEntity getRoles() {
        return roles;
    }

    public String getMiddleName() {
        return middleName;
    }
}