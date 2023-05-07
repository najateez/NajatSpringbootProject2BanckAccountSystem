package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.transaction.Transaction;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionsRepository extends CrudRepository<Transactions, Integer> {

    //6. Retrieve the account history, including all transactions. (for account entity) + 3.Retrieve all transactions for a specific account
    //getAllTransactionsByAccountId
    List<Transactions> findByAccountId(Integer accountId);

    //2.Retrieve the details of a specific transaction.
    Transactions findById(int transactionId);


    
}



