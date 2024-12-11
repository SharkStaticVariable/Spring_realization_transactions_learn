package org.example.spring_realization_transactions.repository;

import org.example.spring_realization_transactions.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity, Integer> {
}
