package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    //3.Update the account balance when a transaction is made.
    //updateBalanceById
    @Modifying
    @Transactional
    @Query(value = "UPDATE Account s SET s.balance=:balance WHERE s.id =:id")
    void updateBalanceById(@Param("id") Integer id, @Param("balance") Double balance);

    //2.Retrieve the account balance for a specific account.
    //getBalanceByAccountNumber
    @Query(value = "SELECT s from Account s where s.accountNumber =:accountNumber")  // :accountNumber -> for input
    Account getBalanceByAccountNumber(@Param("accountNumber") Long accountNumber);

    //5.Generate a monthly statement for the account.
    //take as input month and year from transaction_date and account_id from transactions table, will give result balance from account table.
    @Query("SELECT t FROM Transactions t WHERE t.account.id = :accountId AND YEAR(t.transactionDate) = :year AND MONTH(t.transactionDate) = :month")
    List<Transactions> generateAMonthlyStatementForTheAccount(@Param("accountId") Integer accountId, @Param("year") Integer year, @Param("month") Integer month);

    //3.Retrieve the customer's account information, including all their accounts and their current balances. (for customer entity).
    List<Account> findByCustomerId(Integer customerId);
}
