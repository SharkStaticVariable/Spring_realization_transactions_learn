package org.example.spring_realization_transactions.dto;

import lombok.*;

@Value
@ToString
@Data
@Builder
@AllArgsConstructor
public class LoginDto {
    String username;
    String password;

}
