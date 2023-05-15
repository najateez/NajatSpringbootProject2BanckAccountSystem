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

import java.util.List;

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
    public CreditCard applyForANewCreditCard(Integer customerId, Long creditCardNumber) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setCustomer(customer);
        creditCard.setCreditCardNumber(creditCardNumber);
        return creditCardRepository.save(creditCard);
    }

    //3.Approve or reject a credit card application based on the customer's creditworthiness.
    public void approveOrRejectCreditCardApplication(Integer customerId, Double creditScore, Double income) {

        Customer customer = customerRepository.findById(customerId).orElseThrow();

        if (creditScore >= 700) {
            CreditCard creditCardObj = new CreditCard();
            creditCardObj.setCustomer(customer);
            creditCardObj.setCreditScore(creditScore);
            creditCardObj.setIncome(income);
            creditCardObj.setStatus(true); //it means approved.
            creditCardRepository.save(creditCardObj);
        } else {
            CreditCard creditCardObj = new CreditCard();
            creditCardObj.setCustomer(customer);
            creditCardObj.setCreditScore(creditScore);
            creditCardObj.setIncome(income);
            creditCardObj.setStatus(false); //it means approved.
            creditCardRepository.save(creditCardObj);
        }
    }

    //4.Allow the customer to make payments towards their credit card balance.
    public void makePayment(Integer creditCardId, Double paymentAmount) {

        creditCardRepository.makePayment(creditCardId, paymentAmount);
    }

    //5.Calculate the interest on the credit card balance and update it accordingly.
    public Double calculateCreditCardInterest(Integer creditCardId) {

        CreditCard creditCard = creditCardRepository.findById(creditCardId).orElseThrow(() ->
                new RuntimeException("Credit Card not found"));

        Double interest = creditCard.getIncome() * creditCard.getInterestRate();
        Double currentBalance = creditCard.getIncome(); //original income column
        Double updateBalance = currentBalance + interest;  //interest + original income
       // creditCard.setIncome(updateBalance);
        creditCardRepository.save(creditCard);
        return updateBalance;// the result will be displayed postman.
    }

    //6.Generate a report of all credit card balances and payments.
    public List<CreditCard> getAllCreditCards() {

        return creditCardRepository.findAll();
    }

}
