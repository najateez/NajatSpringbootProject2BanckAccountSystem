package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.AccountRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionsRepository transactionsRepository;

    //1.Create a new account for a customer.
    public void createANewAccountForACustomer (@RequestParam Long accountNumber, @RequestParam Double balance, @RequestParam String accountType) {

        Account accountObj = new Account();
        accountObj.setAccountNumber(accountNumber);
        accountObj.setBalance(balance);
        accountObj.setAccountType(accountType);
        accountRepository.save(accountObj);
    }

    //3.Update the account balance when a transaction is made.
    //updateBalanceById
    public void updateBalanceById(Integer id,Double balance) {

        accountRepository.updateBalanceById(id,balance);
    }

    //2.Retrieve the account balance for a specific account.
    //getBalanceByAccountNumber
    public Account getBalanceByAccountNumber(Long accountNumber) {
        Account account = accountRepository.getBalanceByAccountNumber(accountNumber);
        return account;
    }

    //6.Retrieve the account history, including all transactions.
    //getAllTransactionsByAccountId
    public List<Transactions> retrieveTheAccountHistoryIncludingAllTransactions(@RequestParam Integer accountId) throws Exception { //from transactionsRepository

        Optional<Account> accountOptional = accountRepository.findById(accountId); //from accountRepository

        /* if input of account id which we will write in postman exists in db in both tables, show details.
           but if id entered only exist in account, not in transactions will show empty.. but if entered
           wrong id not appear in both tables will show error.
         */
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return transactionsRepository.findByAccountId(account.getId());
        } else {
            throw new Exception("Account not found with id: " + accountId);
        }
    }

    //4. Calculate the interest on the account balance.
    //interest = balance * interestRate
    public Double calculateTheInterestOnTheAccountBalance(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Double balance = account.getBalance();
        Double interestRate = account.getInterestRate();

        return balance * interestRate;
    }

    //5. Generate a monthly statement for the account.
    //take as input month and year from transaction_date and account_id from transactions table, will give result balance from account table.
    public Double generateAMonthlyStatementForTheAccount(Integer accountId, Integer year, Integer month) {
        List<Transactions> transactionsList = accountRepository.generateAMonthlyStatementForTheAccount(accountId, year, month);
        Double balance = 0.0;
        for (Transactions acc : transactionsList) {
            balance += acc.getAccount().getBalance();
        }
        return balance;
    }



}
