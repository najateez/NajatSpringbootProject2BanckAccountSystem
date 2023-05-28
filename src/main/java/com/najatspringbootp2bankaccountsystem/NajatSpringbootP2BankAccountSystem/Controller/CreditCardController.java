package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.AccountService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    //1.Create a new credit card application for a customer.
    //insert CreditCard
    //exp: localhost:8080/creditCard/createANewCreditCard?creditCardNumber=4645657575
    @RequestMapping(value = "creditCard/createANewCreditCard", method = RequestMethod.POST)
    public void createANewCreditCard(@RequestParam Long creditCardNumber) {

        creditCardService.createANewCreditCard(creditCardNumber);
    }

    //3.Approve or reject a credit card application based on the customer's creditworthiness
    //exp: localhost:8080/creditCard/approveOrRejectLoanApplication?customerId=4&creditScore=880&income=110.6
    @PostMapping("creditCard/approveOrRejectCreditCardApplication")
    public void approveOrRejectCreditCardApplication(@RequestParam Integer customerId, @RequestParam Double creditScore, @RequestParam Double income) {
        creditCardService.approveOrRejectCreditCardApplication(customerId, creditScore, income);
    }

    //4.Allow the customer to make payments towards their credit card balance.
    //exp: localhost:8080/creditCard/makePayment?creditCardId=2&paymentAmount=10
    /* it will subtract income column value which is in db table CreditCard, depend of paymentAmount you
       gave in postman.
       so income will be updated.
     */
    @PostMapping("creditCard/makePayment")
    public ResponseEntity<String> makePayment(@RequestParam Integer creditCardId, @RequestParam Double paymentAmount) {
        creditCardService.makePayment(creditCardId, paymentAmount);
        return ResponseEntity.ok("Payment made successfully");
    }

    //5. Calculate the interest on the credit card balance and update it accordingly.
    //exp:localhost:8080/creditCard/calculateCreditCardInterest?creditCardId=21
    @GetMapping("creditCard/calculateCreditCardInterest")
    public Double calculateCreditCardInterest(@RequestParam Integer creditCardId) {
        return creditCardService.calculateCreditCardInterest(creditCardId);
    }

    //6.Generate a report of all credit card balances and payments.
    //exp: localhost:8080/creditCard/GenerateAReportOfAllCreditCardBalancesAndPayments
    //will display all details from CreditCard table with (fk) information of it customer, from customer table.
    @GetMapping("creditCard/GenerateAReportOfAllCreditCardBalancesAndPayments")
    public List<CreditCard> getAllCreditCards() {

        return creditCardService.getAllCreditCards();
    }

}
