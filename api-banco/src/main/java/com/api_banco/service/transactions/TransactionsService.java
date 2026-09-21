package com.api_banco.service.transactions;

import com.api_banco.dto.requestDTO.transaction.TransactionRequestDTO;
import com.api_banco.dto.responseDTO.transaction.TransactionResponseDTO;
import com.api_banco.entity.account.AccountEntity;
import com.api_banco.entity.transaction.TransactionEntity;
import com.api_banco.exception.account.AccountException;
import com.api_banco.repository.account.AccountRepository;
import com.api_banco.repository.transaction.TransactionRespository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionsService {
    private final TransactionRespository transactionRespository;
    private final AccountRepository accountRepository;

    public TransactionsService(TransactionRespository transactionRespository, AccountRepository accountRepository) {
        this.transactionRespository = transactionRespository;
        this.accountRepository = accountRepository;
    }

    private TransactionResponseDTO transformResponse(TransactionEntity transactionEntity) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        transactionResponseDTO.setId(transactionEntity.getId());
        transactionResponseDTO.setTypeTransaction(transactionEntity.getTypeTransaction());
        transactionResponseDTO.setValueAmount(transactionEntity.getValueAmount());
        transactionResponseDTO.setDateTransaction(transactionEntity.getDateTransaction());
        transactionResponseDTO.setSourceAccountId(transactionEntity.getSourceAccount().getId());
        transactionResponseDTO.setDestinationAccountId(transactionEntity.getDestinationAccount().getId());

        return transactionResponseDTO;
    }

    public TransactionResponseDTO registertransactions(TransactionRequestDTO transactionRequestDTO) {
        Optional<AccountEntity> sourceAccount = accountRepository.findById(transactionRequestDTO.getSourceAccountId());

        if (sourceAccount.isEmpty()) {
            throw new AccountException("Conta de origem inválida");
        }
        Optional<AccountEntity> destinationAccount = accountRepository.findById(transactionRequestDTO.getDestinationAccountId());

        if (destinationAccount.isEmpty()) {
            throw new AccountException("A conta de destino é inválida");
        }

        TransactionEntity transactionRegistred = new TransactionEntity();

        transactionRegistred.setTypeTransaction(transactionRequestDTO.getTypeTransaction());
        transactionRegistred.setValueAmount(transactionRequestDTO.getValueAmount());
        transactionRegistred.setSourceAccount(sourceAccount.get());
        transactionRegistred.setDestinationAccount(destinationAccount.get());

        transactionRespository.save(transactionRegistred);

        return transformResponse(transactionRegistred);
    }

    @Transactional
   public TransactionResponseDTO transferMoney(TransactionRequestDTO transactionRequestDTO) {
        Optional<AccountEntity> accountSourceFound = accountRepository.findById(transactionRequestDTO.getSourceAccountId());
        Optional<AccountEntity> accountDestinationFound = accountRepository.findById(transactionRequestDTO.getDestinationAccountId());

        if (accountSourceFound.isEmpty() || accountDestinationFound.isEmpty()) {
            throw new AccountException("Transação não realizada, uma das contas não existem no sistema");
        }

        if (!accountSourceFound.get().isStatusAccount() ||  !accountDestinationFound.get().isStatusAccount()) {
            throw new AccountException("Uma das contas não está ativa");
        }

        if (accountSourceFound.get().getCurrentBalance().compareTo(transactionRequestDTO.getValueAmount()) < 0) {
            throw new AccountException("Saldo insuficiente");
        }

        accountSourceFound.get().setCurrentBalance(accountSourceFound.get().getCurrentBalance().subtract(transactionRequestDTO.getValueAmount()));
        accountDestinationFound.get().setCurrentBalance(accountDestinationFound.get().getCurrentBalance().add(transactionRequestDTO.getValueAmount()));

        TransactionEntity transactionRegistred = new TransactionEntity();

        transactionRegistred.setTypeTransaction(transactionRequestDTO.getTypeTransaction());
        transactionRegistred.setValueAmount(transactionRequestDTO.getValueAmount());
        transactionRegistred.setDestinationAccount(accountDestinationFound.get());
        transactionRegistred.setSourceAccount(accountSourceFound.get());

        transactionRespository.save(transactionRegistred);

        return transformResponse(transactionRegistred);
   }
}
