package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Component
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    //1.Create a new account for a customer.
    //exp: localhost:8080/account/createANewAccountForACustomer?accountNumber=423534546454&balance=9.879&accountType=Savings
    @RequestMapping(value = "account/createANewAccountForACustomer", method = RequestMethod.POST)
    public void createANewAccountForACustomer(@RequestParam Long accountNumber, @RequestParam Double balance, @RequestParam String accountType) {

        accountService.createANewAccountForACustomer(accountNumber, balance, accountType);
    }

    //3.Update the account balance when a transaction is made.
    //UpdateBalanceById
    //exp: localhost:8080/account/getUpdateBalanceById?id=1&balance=33.9
    @RequestMapping(value = "account/getUpdateBalanceById", method = RequestMethod.POST)
    public void getUpdateBalanceById(@RequestParam Integer id, @RequestParam Double balance) {
        accountService.getUpdateBalanceById(id, balance);
    }

    //2.Retrieve the account balance for a specific account.
    //getBalanceByAccountNumber
    //exp: localhost:8080/account/getBalanceByAccountNumber?accountNumber=5464656464
    @RequestMapping(value = "account/getBalanceByAccountNumber", method = RequestMethod.GET)
    public Account getBalanceByAccountNumber(@RequestParam Long accountNumber) {
        Account account = accountService.getBalanceByAccountNumber(accountNumber);
        return account;
    }

    //6.Retrieve the account history, including all transactions.
    //getAllTransactionsByAccountId
    //Here to make relationship between account table and transactions table (FK) Account (up) to (down) Transactions from table Account. and input from Account Table (id). we should find by id and write code in transactionRepository and accountRepository both.
    //full record of account id entered will appear with (fk) transaction and customer.
    //exp: localhost:8080/account/getAccountHistory?id=1
    @GetMapping("account/getAccountHistory")
    public List<Transactions> getAccountHistory(@RequestParam Integer id) throws Exception {
        return accountService.getAccountHistory(id);
    }

    //4. Calculate the interest on the account balance.
    //exp: localhost:8080/account/getAccountInterest?id=2
    //interest = balance * interestRate -> the result of interest will show in postman.
    @GetMapping("account/getAccountInterest")
    public ResponseEntity<Double> getAccountInterest(@RequestParam Integer id) {
        Double interest = accountService.calculateInterest(id);
        return ResponseEntity.ok(interest); //http status code 200 ok.
    }

    //5. Generate a monthly statement for the account.
    //Transactions for an account in a month
    //get all transactions for a specific account within a specific month.
    //exp: localhost:8080/account/getAccountBalanceForMonth?id=1&year=2023&month=05
    @GetMapping("account/getAccountBalanceForMonth")
    public ResponseEntity<Double> getAccountBalanceForMonth(@RequestParam Integer accountId, @RequestParam Integer year, @RequestParam Integer month) {
        Double transaction_amount = accountService.getAccountBalanceForMonth(accountId, year, month);
        return ResponseEntity.ok(transaction_amount);
    }
}
