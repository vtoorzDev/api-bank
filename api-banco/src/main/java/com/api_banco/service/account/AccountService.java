package com.api_banco.service.account;

import com.api_banco.dto.requestDTO.account.AccountRequestDTO;
import com.api_banco.dto.responseDTO.account.AccountResponseDTO;
import com.api_banco.entity.account.AccountEntity;
import com.api_banco.entity.client.ClientEntity;
import com.api_banco.exception.account.AccountException;
import com.api_banco.repository.account.AccountRepository;
import com.api_banco.repository.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        accountResponseDTO.setCurrentBalance(accountEntity.getCurrentBalance());
        accountResponseDTO.setStatusAccount(accountEntity.isStatusAccount());
        accountResponseDTO.setClientCpf(accountEntity.getClient().getCpf());
        accountResponseDTO.setClientName(accountEntity.getClient().getName());

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
        ClientEntity client = clientFound.get();

        accountcreated.setAccountNumber(accountRequestDTO.getAccountNumber());
        accountcreated.setAgency(accountRequestDTO.getAgency());
        accountcreated.setCurrentBalance(client.getWage());
        accountcreated.setStatusAccount(true);
        accountcreated.setClient(clientFound.get());

        accountRepository.save(accountcreated);

        return transformResponse(accountcreated);
    }
    public List<AccountResponseDTO> findAllAccounts(){
        List<AccountEntity> accounts = accountRepository.findAll();
        List<AccountResponseDTO> accountsResponse = new ArrayList<>();

        if (accounts.isEmpty()){
            throw new AccountException("Nenhuma conta cadastrada no sistema");
        }

        for (AccountEntity account : accounts) {
            accountsResponse.add(transformResponse(account));
        }
        return accountsResponse;
        }
    public AccountResponseDTO findByIdAccount(Long id) {
        Optional<AccountEntity> accountFound = accountRepository.findById(id);

        if (accountFound.isEmpty()) {
            throw new AccountException("Conta não encontrada");
        }
        return transformResponse(accountFound.get());
    }

    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO, Long id) {
        Optional<AccountEntity> accoutFound = accountRepository.findById(id);

        if (accoutFound.isEmpty()) {
            throw new AccountException("Conta não encontrada no sistema");
        }

        AccountEntity accountUpdate = accoutFound.get();

        accountUpdate.setAccountNumber(accountRequestDTO.getAccountNumber());
        accountUpdate.setAgency(accountRequestDTO.getAgency());


        accountRepository.save(accountUpdate);

        return transformResponse(accountUpdate);
    }

    public void deleteAccount(Long id) {
        Optional<AccountEntity> accountFoud = accountRepository.findById(id);

        if (accountFoud.isEmpty()) {
            throw new AccountException("Conta não encontrada");
        }

        AccountEntity accountDelete = accountFoud.get();
        accountRepository.delete(accountDelete);

    }
}
