package org.example.spring_realization_transactions.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountsDTO {
    private BigDecimal balance;
    private LocalDate creationDate;
    private int number;

    public AccountsDTO(Integer id, BigDecimal balance, LocalDate creationDate, int number ) {
    }

}
