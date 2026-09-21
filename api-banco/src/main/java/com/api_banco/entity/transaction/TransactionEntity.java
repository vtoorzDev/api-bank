package com.api_banco.entity.transaction;

import com.api_banco.entity.account.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeTransaction;

    private BigDecimal valueAmount;

    private LocalDateTime dateTransaction = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private AccountEntity sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private AccountEntity destinationAccount;
}