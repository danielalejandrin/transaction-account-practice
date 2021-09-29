package com.pichincha.backend.test.rest;

import com.pichincha.backend.test.dto.AccountDto;
import com.pichincha.backend.test.dto.NewTransactionDto;
import com.pichincha.backend.test.dto.TransactionDto;
import com.pichincha.backend.test.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService aService;


	public AccountController(AccountService accountService) {
		this.aService = accountService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto getAccount(@PathVariable Long id) {
		return aService.getAccount(id);
	}

	@GetMapping("/{id}/transactions")
	@ResponseStatus(HttpStatus.OK)
	public List<TransactionDto> getTransactionsForAccount(@PathVariable Long id) {
		return aService.getTransactionsForAccount(id);
	}
	
	@PostMapping(value="/{id}/transactions")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addTransaction(@RequestBody NewTransactionDto newTransaction) {
		return aService.addTransaction(newTransaction);
	}
	
}
