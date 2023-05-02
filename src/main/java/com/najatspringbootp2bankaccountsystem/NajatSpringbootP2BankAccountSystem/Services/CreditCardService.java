package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CreditCardRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    // Create a new credit card application for a customer.
    public void createANewCreditCard(@RequestParam Long creditCardNumber) {

        CreditCard creditCardObj = new CreditCard();
        creditCardObj.setCreditCardNumber(creditCardNumber);
        creditCardRepository.save(creditCardObj);
    }
}
