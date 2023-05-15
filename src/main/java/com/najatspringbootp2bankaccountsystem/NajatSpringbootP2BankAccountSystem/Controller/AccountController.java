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
    //exp: localhost:8080/account/updateBalanceById?id=1&balance=33.9
    @RequestMapping(value = "account/updateBalanceById", method = RequestMethod.POST)
    public void updateBalanceById(@RequestParam Integer id, @RequestParam Double balance) {
        accountService.updateBalanceById(id, balance);
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
    //exp: localhost:8080/account/retrieveTheAccountHistoryIncludingAllTransactions?id=5.  //accountId
    @GetMapping("account/retrieveTheAccountHistoryIncludingAllTransactions")
    public List<Transactions> retrieveTheAccountHistoryIncludingAllTransactions(@RequestParam Integer id) throws Exception {
        return accountService.retrieveTheAccountHistoryIncludingAllTransactions(id);
    }

    //4. Calculate the interest on the account balance.
    //exp: localhost:8080/account/calculateTheInterestOnTheAccountBalance?id=1
    //interest = balance * interestRate -> the result of interest will show in postman.
    @GetMapping("account/calculateTheInterestOnTheAccountBalance")
    public ResponseEntity<Double> calculateTheInterestOnTheAccountBalance(@RequestParam Integer id) {
        Double interest = accountService.calculateTheInterestOnTheAccountBalance(id);
        return ResponseEntity.ok(interest); //http status code 200 ok.
    }

    //5. Generate a monthly statement for the account.
    //take as input month and year from transaction_date and account_id from transactions table, will give result balance from account table.
    //exp: localhost:8080/account/generateAMonthlyStatementForTheAccount?accountId=2&year=1996&month=05
    @GetMapping("account/generateAMonthlyStatementForTheAccount")
    public ResponseEntity<Double> generateAMonthlyStatementForTheAccount(@RequestParam Integer accountId, @RequestParam Integer year, @RequestParam Integer month) {
        Double balance = accountService.generateAMonthlyStatementForTheAccount(accountId, year, month);
        return ResponseEntity.ok(balance);
    }
}
