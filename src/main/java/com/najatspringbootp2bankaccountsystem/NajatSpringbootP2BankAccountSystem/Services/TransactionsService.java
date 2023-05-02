package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    //Create a new transaction for a specific account
    public void createANewTransactions (@RequestParam Double transactionAmount) {

        Transactions transactionsObj = new Transactions();
        transactionsObj.setTransactionAmount(transactionAmount);
        transactionsRepository.save(transactionsObj);
    }
}
