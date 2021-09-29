package com.pichincha.backend.test.service;


import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.model.Account;
import com.pichincha.backend.test.model.Transaction;
import com.pichincha.backend.test.repository.AccountRepository;
import com.pichincha.backend.test.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {


	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	public AccountDto getAccount(Long id) {
		return accountRepository.findById(id)
			.map(account -> new AccountDto(account.getNumber(), account.getType(), account.getCreationDate()))
			.orElse(null);
	}

	/**
	 * Returns a list of all transactions for a account with passed id.
	 *
	 * @param accountId id of the account
	 * @return list of transactions sorted by creation date descending - most recent first
	 */
	public List<TransactionDto> getTransactionsForAccount(Long accountId) {

			List<Transaction> transactions = transactionRepository.findByAccount_Id(accountId);

			return transactions.stream()
					.map(transaction -> new TransactionDto(transaction.getId(),
														transaction.getComment(),
														transaction.getType(),
							transaction.getCreationDate()))
					.collect(Collectors.toList());

	}

	/**
	 * Creates a new transaction
	 *
	 * @param newTransactionDto data of new transaction
	 * @return id of the created transaction
	 * @throws IllegalArgumentException if there is no account for passed newTransactionDto.accountId
	 */
	public Long addTransaction(NewTransactionDto newTransactionDto) {

		if(newTransactionDto.getAccountId() == null ){
			throw  new IllegalArgumentException("Account Id it's required");
		}
		Account referencedAccount = accountRepository.findById(newTransactionDto.getAccountId()).orElse(null);
		Transaction createTransaction = new Transaction(null, newTransactionDto.getComment(), newTransactionDto.getType(), LocalDateTime.now(), null);
		createTransaction.setAccount(referencedAccount);
		transactionRepository.save(createTransaction);
		return createTransaction.getId();
	}

}
