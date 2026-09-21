package com.api_banco.dto.requestDTO.transaction;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDTO {

    @NotBlank
    private String typeTransaction;

    @NotNull
    @Positive
    private BigDecimal valueAmount;

    @NotNull
    private Long sourceAccountId;

    @NotNull
    private Long destinationAccountId;
}
