package com.api_banco.dto.responseDTO.account;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private String agency;
    private BigDecimal currentBalance;
    private boolean statusAccount;
    private String clientCpf;
    private String clientName;
}
