package com.api_banco.repository.transaction;

import com.api_banco.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRespository extends JpaRepository<TransactionEntity, Long> {
    Optional<TransactionEntity> findById(Long id);
}
