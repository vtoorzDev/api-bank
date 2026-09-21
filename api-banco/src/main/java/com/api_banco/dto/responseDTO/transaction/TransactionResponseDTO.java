package com.api_banco.dto.responseDTO.transaction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponseDTO {
    private Long id;
    private String typeTransaction;
    private BigDecimal valueAmount;
    private LocalDateTime dateTransaction = LocalDateTime.now();
    private Long sourceAccountId;
    private Long destinationAccountId;

}
