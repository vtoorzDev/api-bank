package com.api_banco.controller.transaction;

import com.api_banco.dto.requestDTO.transaction.TransactionRequestDTO;
import com.api_banco.dto.responseDTO.transaction.TransactionResponseDTO;
import com.api_banco.service.transactions.TransactionsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionsService transactionsService;

    public TransactionController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("transfer")
    public TransactionResponseDTO transferMoney(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionsService.transferMoney(transactionRequestDTO);
    }
}
