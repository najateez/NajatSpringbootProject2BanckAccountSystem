package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@RestController
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;

    //1.Create a new transaction for a specific account.
    //exp: localhost:8080/transactions/createANewTransactions?transactionAmount=727.8
    @RequestMapping(value = "transactions/createANewTransactions", method = RequestMethod.POST)
    public void createANewTransactions(@RequestParam Double transactionAmount) {

        transactionsService.createANewTransactions(transactionAmount);
    }

}
