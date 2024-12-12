package org.example.spring_realization_transactions.repository;

import org.example.spring_realization_transactions.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaTransactionsRepository")
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Integer> {
}
