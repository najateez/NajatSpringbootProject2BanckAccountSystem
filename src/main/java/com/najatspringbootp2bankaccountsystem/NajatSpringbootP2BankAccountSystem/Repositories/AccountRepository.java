package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    // Update the account balance when a transaction is made.
    // getUpdateBalanceById
    @Modifying
    @Transactional
    @Query(value = "UPDATE Account s SET s.balance=:balance WHERE s.id =:id")
    void getUpdateBalanceById(@Param("id") Integer id, @Param("balance") Double balance);

    //Retrieve the account balance for a specific account.
    //getBalanceByAccountNumber
    @Query(value = "SELECT s from Account s where s.accountNumber =:accountNumber")  // :accountNumber -> for user input
    Account getBalanceByAccountNumber(@Param("accountNumber") Long accountNumber);


}
