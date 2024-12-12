package org.example.spring_realization_transactions.repository;

import org.example.spring_realization_transactions.entity.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaTransactionsLogRepository")
public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, Integer> {
}
