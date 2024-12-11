package org.example.spring_realization_transactions.mapper;

import org.example.spring_realization_transactions.dto.AccountsDTO;
import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountsReadMapper implements Mapper<AccountsEntity, AccountsDTO>{
    @Override
    public AccountsDTO map(AccountsEntity object) {
        return new AccountsDTO(
                object.getId(),
                object.getBalance(),
                object.getCreationDate(),
                object.getNumber()
        );
    }
}
