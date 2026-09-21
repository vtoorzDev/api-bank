package com.api_banco.repository.account;

import com.api_banco.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByAccountNumber(String accountNumber);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
