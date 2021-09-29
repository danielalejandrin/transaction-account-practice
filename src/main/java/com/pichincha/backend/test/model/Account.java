package com.pichincha.backend.test.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String number;

	@Column(length = 70)
	private String type;

	private LocalDateTime creationDate;



	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transaction> transactions;
/*
	public void addTransaction(Transaction transaction) {
		if(transactions.contains(transaction)) return;
		transactions.add(transaction);
		transaction.setAccount(this);
	}

	public void removeTransaction(Transaction transaction){
		if(!transactions.contains(transaction)){
			return;
		}
		transactions.remove(transaction);
		transaction.setAccount(null);
	}*/
}
