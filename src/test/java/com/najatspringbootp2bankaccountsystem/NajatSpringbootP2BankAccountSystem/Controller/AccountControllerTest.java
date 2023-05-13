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
    void getUpdateBalanceById() {

        accountController.getUpdateBalanceById(2, 88.1);
    }

    @Test
    void getBalanceByAccountNumber() {
      //  accountController.getBalanceByAccountNumber(85584834);
    }

    @Test
    void getAccountHistory() throws Exception {
        List<Transactions> accountHistory= accountController.getAccountHistory(4); //account_id
        assertEquals(727.8, accountHistory.get(0).getTransactionAmount()); //first 4
    }

    @Test
    void getAccountInterest() {
        accountController.getAccountInterest(1);
    }

    @Test
    void getAccountBalanceForMonth() {
      //  accountController.getAccountBalanceForMonth(1,1996,05);
    }
}