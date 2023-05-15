package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    @Test
    void createANewCustomer() {

        customerController.createANewCustomer("najat",98765432,"najateez96@gmail.com");
    }

    @Test
    void updateCustomerEmailOrPhoneById() {

        customerController.updateCustomerEmailOrPhoneById(1, 998, "areenhmm@a.com");
    }

    @Test
    void customersAccountInformation() {

        customerController.customersAccountInformation(1);
    }

    @Test
    void applyForANewLoan() {
    //    customerController.applyForLoan(1,33.2);
    }

    @Test
    void applyForANewCreditCard() {
      //  customerController.applyForCreditCard();
    }

    @Test
    void getStatusAllActiveLoansForACustomer() {

        List<Loan> activeLoans = customerController.getStatusAllActiveLoansForACustomer();
        assertEquals(7770.55, activeLoans.get(0).getLoanAmount());
    }

    @Test
    void getStatusAllInActiveLoansForACustomer() {

        List<Loan> inActiveLoans = customerController.getStatusAllInActiveLoansForACustomer();
        assertEquals(789005, inActiveLoans.get(1).getLoanAmount());
    }

    @Test
    void getStatusAllActiveCreditCardForACustomer() {
        List<CreditCard> activeCreditCard = customerController.getStatusAllActiveCreditCardForACustomer();
        assertEquals(789005, activeCreditCard.get(1).getCreditCardNumber());
    }

    @Test
    void getStatusAllInActiveCreditCardForACustomer() {
        List<CreditCard> inActiveCreditCard = customerController.getStatusAllInActiveCreditCardForACustomer();
        assertEquals(9112233, inActiveCreditCard.get(2).getCreditCardNumber());
    }

    @Test
    void customersTransactionsHistoryAcrossAllTheirAccountsByAccountId() {

       List<Transactions> account= customerController.customersTransactionsHistoryAcrossAllTheirAccountsByAccountId(2);
        assertEquals(181.5, account.get(0).getTransactionAmount());
    }
}