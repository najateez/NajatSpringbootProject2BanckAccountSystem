package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountControllerTest {

    @Autowired
    AccountController accountController;

    @Test
    void createANewAccountForACustomer() {

     //   accountController.createANewAccountForACustomer(2137, 9.9, "Checking");
    }

    @Test
    void updateBalanceById() {

        accountController.updateBalanceById(2, 1.2);
    }

    @Test
    void getBalanceByAccountNumber() {
      //  accountController.getBalanceByAccountNumber(85584834);
    }

    @Test
    void retrieveTheAccountHistoryIncludingAllTransactions() throws Exception {
        List<Transactions> accountHistory= accountController.retrieveTheAccountHistoryIncludingAllTransactions(4); //account_id
        assertEquals(727.8, accountHistory.get(0).getTransactionAmount()); //first 4
    }

    @Test
    void calculateTheInterestOnTheAccountBalance() {
        accountController.calculateTheInterestOnTheAccountBalance(1);
    }

    @Test
    void generateAMonthlyStatementForTheAccount() {
      //  accountController.getAccountBalanceForMonth(1,1996,05);
    }
}