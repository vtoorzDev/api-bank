package com.api_banco.dto.requestDTO.client;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ClientRequestDTO {
    @Column(unique = true)
    @NotBlank(message = "O campo de cpf é obrigatório")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank(message = "O campo de nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo de telefone é obrigatório")
    private String phone;

    @Positive
    @NotNull
    private BigDecimal wage;

}
