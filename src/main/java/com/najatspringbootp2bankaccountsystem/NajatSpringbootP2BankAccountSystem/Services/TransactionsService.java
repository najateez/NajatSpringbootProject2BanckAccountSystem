package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    //1.Create a new transaction for a specific account
    public void createANewTransactions (@RequestParam Double transactionAmount) {

        Transactions transactionsObj = new Transactions();
        transactionsObj.setTransactionAmount(transactionAmount);
        transactionsRepository.save(transactionsObj);
    }

    //2.Retrieve the details of a specific transaction.
    public Transactions getTransactionById(int transactionId) {
        return transactionsRepository.findById(transactionId);
    }

    //3.Retrieve all transactions for a specific account
    public List<Transactions> getTransactionsForAccount(Integer accountId) {
        return transactionsRepository.findByAccountId(accountId);
    }

    //4.Calculate the fees or charges associated with the transaction.
    /* you should give in postman transactionAmount more than zero then will multiply with 0.02
       and give result of fees. otherwise the result will be zero.
     */

    public double calculateTransactionFee(Transactions transaction) {

        double fee = 0.0;

        if (transaction.getTransactionAmount() > 0) {
            fee = Math.abs(transaction.getTransactionAmount()) * 0.02;
        }

        return fee;
    }

}
