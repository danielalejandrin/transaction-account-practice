package com.pichincha.backend.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private String type;
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

//    public void setAccount(Account account) {
//        if(sameAccount(account)){
//            return;
//        }
//        Account oldAccount = this.account;
//        this.account = account;
//        if(oldAccount != null){
//            oldAccount.removeTransaction(this);
//        }
//        if(account != null){
//            account.addTransaction(this);
//        }
//    }

//    private boolean sameAccount(Account newAccount){
//        return this.account == null ? newAccount == null : this.account.equals(newAccount);
//    }
}
