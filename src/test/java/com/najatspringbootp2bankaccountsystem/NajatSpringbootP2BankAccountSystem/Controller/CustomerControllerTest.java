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
    void getUpdateCustomerById() {

        customerController.getUpdateCustomerById(1, "Areen", 91234567, "areen@a.com");
    }

    @Test
    void getCustomerAccounts() {

        customerController.getCustomerAccounts(1);
    }

    @Test
    void applyForLoan() {
    //    customerController.applyForLoan(1,33.2);
    }

    @Test
    void applyForCreditCard() {
      //  customerController.applyForCreditCard();
    }

    @Test
    void getStatusAllActiveLoans() {

        List<Loan> activeLoans = customerController.getStatusAllActiveLoans();
        assertEquals(7770.55, activeLoans.get(0).getLoanAmount());
    }

    @Test
    void getStatusAllInActiveLoans() {

        List<Loan> inActiveLoans = customerController.getStatusAllInActiveLoans();
        assertEquals(789005, inActiveLoans.get(1).getLoanAmount());
    }

    @Test
    void getStatusAllActiveCreditCard() {
        List<CreditCard> activeCreditCard = customerController.getStatusAllActiveCreditCard();
        assertEquals(789005, activeCreditCard.get(1).getCreditCardNumber());
    }

    @Test
    void getStatusAllInActiveCreditCard() {
        List<CreditCard> inActiveCreditCard = customerController.getStatusAllInActiveCreditCard();
        assertEquals(9112233, inActiveCreditCard.get(2).getCreditCardNumber());
    }

    @Test
    void getTransactionsByCustomerId() {

       List<Transactions> customer= customerController.getTransactionsByCustomerId(2);
        assertEquals(181.5, customer.get(0).getTransactionAmount());
    }
}