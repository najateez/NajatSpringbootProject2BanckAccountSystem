package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoanControllerTest {
    @Autowired
    LoanController loanController;

    @Test
    void createANewLoan() {
        loanController.createANewLoan(123.9);

    }

    @Test
    void approveOrRejectLoanApplication() {
        loanController.approveOrRejectLoanApplication(1, 7.9, 100.1);
    }

    @Test
    void calculateLoanInterest() {
        loanController.calculateLoanInterest(8);
    }

    @Test
    void makePayment() {
      //  loanController.makePayment(41, 112.6);
    }

    @Test
    void getAllLoans() {
        List<Loan> getAllLoans = loanController.getAllLoans();
        assertEquals(7770.55, getAllLoans.get(0).getLoanAmount());
    }
}