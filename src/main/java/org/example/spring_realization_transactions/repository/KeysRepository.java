package org.example.spring_realization_transactions.repository;

import org.example.spring_realization_transactions.entity.KeysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeysRepository extends JpaRepository<KeysEntity, Integer> {
}
