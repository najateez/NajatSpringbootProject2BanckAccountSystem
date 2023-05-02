package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    // Create a new account for a customer.
    //exp: localhost:8080/account/createANewAccountForACustomer?accountNumber=423534546454&balance=9.879&accountType=HSBC
    @RequestMapping(value = "account/createANewAccountForACustomer", method = RequestMethod.POST)
    public void createANewAccountForACustomer(@RequestParam Long accountNumber, @RequestParam Double balance, @RequestParam String accountType) {

        accountService.createANewAccountForACustomer(accountNumber,balance, accountType);
    }

    // Update the account balance when a transaction is made.
    //UpdateBalanceById
    //exp: localhost:8080/account/getUpdateBalanceById?id=1&balance=33.9
    @RequestMapping(value = "account/getUpdateBalanceById", method = RequestMethod.POST)
    public void getUpdateBalanceById(@RequestParam Integer id,@RequestParam Double balance) {
        accountService.getUpdateBalanceById(id, balance);
    }

    //Retrieve the account balance for a specific account.
    //getBalanceByAccountNumber
    //exp: localhost:8080/account/getBalanceByAccountNumber?accountNumber=5464656464
    @RequestMapping(value = "account/getBalanceByAccountNumber", method = RequestMethod.GET)
    public Account getBalanceByAccountNumber(@RequestParam Long accountNumber){
        Account account = accountService.getBalanceByAccountNumber(accountNumber);
        return account;
    }


}
