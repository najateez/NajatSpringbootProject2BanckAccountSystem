package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@RestController
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;
    @Autowired
    TransactionsRepository transactionsRepository;

    //1.Create a new transaction for a specific account.
    //exp: localhost:8080/transactions/createANewTransactions?transactionAmount=727.8
    @RequestMapping(value = "transactions/createANewTransactions", method = RequestMethod.POST)
    public void createANewTransactions(@RequestParam Double transactionAmount) {

        transactionsService.createANewTransactions(transactionAmount);
    }

    //2.Retrieve the details of a specific transaction.
    //exp: localhost:8080/transactions/getTransactionById?transactionId=1
    @GetMapping("transactions/getTransactionById")
    public Transactions getTransactionById(@RequestParam int transactionId) {
        return transactionsService.getTransactionById(transactionId);
    }

    //3.Retrieve all transactions for a specific account
    //exp: localhost:8080/transactions/getTransactionsForAccount?accountId=4
    @GetMapping("transactions/getTransactionsForAccount")
    public List<Transactions> getTransactionsForAccount(@RequestParam Integer accountId) {
        return transactionsService.getTransactionsForAccount(accountId);
    }

    //4.Calculate the fees or charges associated with the transaction.
    //exp: localhost:8080/transactions/calculateFee? , in body write: {"transactionAmount": 55.2}
    @PostMapping("transactions/calculateFee")
    public double calculateTransactionFee(@RequestBody Transactions transaction) {
        return transactionsService.calculateTransactionFee(transaction);
    }

    //5.Generate a report of all transactions within a specific time period.
    //exp: localhost:8080/transactions/generateReport?startDate=2000-11-11&endDate=2023-12-12.
    /* the information record from db of tables transactions, account, customer will appear between these 2
       dates: startDate and endDate -> depend of transactionsDate column in db.
     */
    @GetMapping("transactions/generateReport")
    public ResponseEntity<List<Transactions>> generateReport(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Transactions> transactions = transactionsService.findAllByTransactionDateBetween(startDate, endDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    //6.Reverse a transaction in case of an error.
    //exp: localhost:8080/transactions/reverseTransaction?transactionId=3
    //will post the result in new row in db table transactions.
    @PostMapping("transactions/reverseTransaction")
    public ResponseEntity<Transactions> reverseTransaction(@RequestParam Integer transactionId) {
        try {
            Transactions reverseTransaction = transactionsService.reverseTransaction(transactionId);
            return ResponseEntity.ok(reverseTransaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
