package com.api_banco.dto.requestDTO.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDTO {
    @NotBlank
    private String accountNumber;
    @NotBlank
    private String agency;
    @NotNull
    private BigDecimal currentBalance;
    @NotNull
    private Long clientId;

}
