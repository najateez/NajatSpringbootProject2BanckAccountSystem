package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.AccountService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    // Create a new credit card application for a customer.
    //insert CreditCard
    //exp: localhost:8080/creditCard/createANewCreditCard?creditCardNumber=4645657575
    @RequestMapping(value = "creditCard/createANewCreditCard", method = RequestMethod.POST)
    public void createANewCreditCard(@RequestParam Long creditCardNumber) {

        creditCardService.createANewCreditCard(creditCardNumber);
    }
}
