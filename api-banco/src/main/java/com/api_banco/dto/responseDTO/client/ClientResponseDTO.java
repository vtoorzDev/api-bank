package com.api_banco.dto.responseDTO.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private BigDecimal wage;
}
