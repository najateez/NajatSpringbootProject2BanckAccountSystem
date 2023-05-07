package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CreditCardRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    CustomerRepository customerRepository;

    //1.Create a new credit card application for a customer.
    public void createANewCreditCard(@RequestParam Long creditCardNumber) {

        CreditCard creditCardObj = new CreditCard();
        creditCardObj.setCreditCardNumber(creditCardNumber);
        creditCardRepository.save(creditCardObj);
    }

    // 4.Apply for a new loan or credit card.
    public CreditCard applyForCreditCard(Integer customerId, Long creditCardNumber) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setCustomer(customer);
        creditCard.setCreditCardNumber(creditCardNumber);
        return creditCardRepository.save(creditCard);
    }
}
