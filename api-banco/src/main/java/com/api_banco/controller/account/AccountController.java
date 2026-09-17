package com.api_banco.controller.account;

import com.api_banco.dto.requestDTO.account.AccountRequestDTO;
import com.api_banco.dto.responseDTO.account.AccountResponseDTO;
import com.api_banco.service.account.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/listAll")
    public List<AccountResponseDTO> listAllAccounts(){
        return accountService.findAllAccounts();
    }

    @GetMapping("/listById")
    public AccountResponseDTO findById(@PathVariable Long id) {
        return accountService.findByIdAccount(id);
    }

    @PutMapping("/updateAccount/{id}")
    public AccountResponseDTO updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.updateAccount(accountRequestDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
