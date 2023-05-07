package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
