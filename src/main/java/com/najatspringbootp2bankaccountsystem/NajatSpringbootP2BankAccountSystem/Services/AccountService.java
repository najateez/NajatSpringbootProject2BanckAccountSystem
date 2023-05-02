package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    // Create a new account for a customer.
    public void createANewAccountForACustomer (@RequestParam Long accountNumber, @RequestParam Double balance, @RequestParam String accountType) {

        Account accountObj = new Account();
        accountObj.setAccountNumber(accountNumber);
        accountObj.setBalance(balance);
        accountObj.setAccountType(accountType);
        accountRepository.save(accountObj);
    }

    // Update the account balance when a transaction is made.
    public void getUpdateBalanceById(Integer id,Double balance) {

        accountRepository.getUpdateBalanceById(id,balance);
    }

    //Retrieve the account balance for a specific account.
    public Account getBalanceByAccountNumber(Long accountNumber) {
        Account account = accountRepository.getBalanceByAccountNumber(accountNumber);
        return account;
    }
}
