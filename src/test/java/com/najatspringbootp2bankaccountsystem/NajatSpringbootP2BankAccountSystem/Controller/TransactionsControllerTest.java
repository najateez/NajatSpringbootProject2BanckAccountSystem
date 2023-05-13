package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TransactionsControllerTest {
    @Autowired
    TransactionsController transactionsController;

    @Test
    void createANewTransactions() {
        transactionsController.createANewTransactions(996.7);
    }

    @Test
    void getTransactionById() {
    //    transactionsController.getTransactionById(842);
    }

    @Test
    void getTransactionsForAccount() {
    //    transactionsController.getTransactionsForAccount(255);
    }

    @Test
    void calculateTransactionFee() {
      //  transactionsController.calculateTransactionFee();
    }

    @Test
    void generateReport() {
      //  transactionsController.generateReport("1996-05-02","1999-05-01");
    }

    @Test
    void reverseTransaction() {
    //    transactionsController.reverseTransaction(1);
    }
}