package com.api_banco.controller.account;

import com.api_banco.dto.requestDTO.account.AccountRequestDTO;
import com.api_banco.dto.responseDTO.account.AccountResponseDTO;
import com.api_banco.service.account.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public AccountResponseDTO registerAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.registerAccount(accountRequestDTO);
    }
}
