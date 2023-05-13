package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CreditCardControllerTest {
    @Autowired
    CreditCardController creditCardController;

    @Test
    void createANewCreditCard() {
      //  creditCardController.createANewCreditCard(444);
    }

    @Test
    void approveOrRejectCreditCardApplication() {
        creditCardController.approveOrRejectCreditCardApplication(5, 990.1, 19.96);
    }

    @Test
    void makePayment() {
        creditCardController.makePayment(24, 2.1); //check income
    }

    @Test
    void calculateCreditCardInterest() {
        creditCardController.calculateCreditCardInterest(25);
    }

    @Test
    void getAllCreditCards() {

        List<CreditCard> getAllCreditCards = creditCardController.getAllCreditCards();
        assertEquals(789005, getAllCreditCards.get(4).getCreditCardNumber());
    }
}