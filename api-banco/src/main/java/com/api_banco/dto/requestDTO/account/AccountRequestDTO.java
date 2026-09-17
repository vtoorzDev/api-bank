package com.api_banco.dto.requestDTO.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDTO {
    @NotBlank
    @Positive
    private String accountNumber;
    @NotBlank
    @Positive
    private String agency;
    @NotNull
    @Positive
    private BigDecimal currentBalance;
    @NotNull
    private Long clientId;

}
