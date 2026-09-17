package com.api_banco.service.account;

import com.api_banco.dto.requestDTO.account.AccountRequestDTO;
import com.api_banco.dto.responseDTO.account.AccountResponseDTO;
import com.api_banco.entity.account.AccountEntity;
import com.api_banco.entity.client.ClientEntity;
import com.api_banco.exception.account.AccountException;
import com.api_banco.repository.account.AccountRepository;
import com.api_banco.repository.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    private AccountResponseDTO transformResponse(AccountEntity accountEntity) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

        accountResponseDTO.setId(accountEntity.getId());
        accountResponseDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountResponseDTO.setAgency(accountEntity.getAgency());
        accountResponseDTO.setStatusAccount(accountEntity.isStatusAccount());
        accountResponseDTO.setCurrentBalance(accountEntity.getCurrentBalance());

        return accountResponseDTO;
    }

    public AccountResponseDTO registerAccount(AccountRequestDTO accountRequestDTO) {
        if (accountRepository.existsByAccountNumber(accountRequestDTO.getAccountNumber())) {
            throw new AccountException("Conta já existe no sistema");
        }

        Optional<ClientEntity> clientFound = clientRepository.findById(accountRequestDTO.getClientId());

        if (clientFound.isEmpty()) {
            throw new AccountException("Cliente não encontrado");
        }

        AccountEntity accountcreated = new AccountEntity();

        accountcreated.setAccountNumber(accountRequestDTO.getAccountNumber());
        accountcreated.setAgency(accountRequestDTO.getAgency());
        accountcreated.setCurrentBalance(accountRequestDTO.getCurrentBalance());
        accountcreated.setStatusAccount(true);
        accountcreated.setClient(clientFound.get());

        accountRepository.save(accountcreated);

        return transformResponse(accountcreated);
    }
}
