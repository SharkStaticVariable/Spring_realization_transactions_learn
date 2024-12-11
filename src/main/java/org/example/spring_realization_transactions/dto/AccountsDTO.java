package org.example.spring_realization_transactions.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountsDTO {
    private double balance;
    private boolean isActive;
    private int number;

    public AccountsDTO(Integer id, BigDecimal balance, LocalDate number, int active) {
    }

}
