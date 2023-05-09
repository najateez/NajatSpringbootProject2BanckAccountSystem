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
    public void getUpdateBalanceById(Integer id,Double balance) {

        accountRepository.getUpdateBalanceById(id,balance);
    }

    //2.Retrieve the account balance for a specific account.
    public Account getBalanceByAccountNumber(Long accountNumber) {
        Account account = accountRepository.getBalanceByAccountNumber(accountNumber);
        return account;
    }

    //6.Retrieve the account history, including all transactions.
    //getAllTransactionsByAccountId
    public List<Transactions> getAccountHistory(@RequestParam Integer accountId) throws Exception { //from transactionsRepository

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
    public Double calculateInterest(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Double balance = account.getBalance();
        Double interestRate = account.getInterestRate();

        return balance * interestRate;
    }

    //5. Generate a monthly statement for the account.
    //Transactions for an account in month
    /* look into Transactions table, give account id, with transactionsDate its year and month, will give
       the result of transactions amount from column of transactions_amount. if you give any wrong input
       in postman the result will be zero.
     */
    public Double getAccountBalanceForMonth(Integer accountId, Integer year, Integer month) {
        List<Transactions> transactions = accountRepository.findTransactionsByAccountAndMonth(accountId, year, month);
        Double transaction_amount = 0.0;
        for (Transactions transaction : transactions) {
            transaction_amount += transaction.getTransactionAmount();
        }
        return transaction_amount;
    }



}
