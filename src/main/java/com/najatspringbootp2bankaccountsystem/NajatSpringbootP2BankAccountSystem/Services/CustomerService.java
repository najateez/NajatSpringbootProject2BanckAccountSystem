package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;


import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.*;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    CreditCardRepository creditCardRepository;


    // 1.Create a new account for a customer.
    public void createANewCustomer(@RequestParam String name, @RequestParam Integer phoneNumber, @RequestParam String email) {

        Customer customerObj = new Customer();
        customerObj.setName(name);
        customerObj.setPhoneNumber(phoneNumber);
        customerObj.setEmail(email);
        customerRepository.save(customerObj);
    }

    //2.Update the customer information, such as their email or phone number.
    public void getUpdateCustomerById(Integer id, String name, Integer phoneNumber, String email) {

        customerRepository.getUpdateCustomerById(id, name, phoneNumber, email);
    }

    //3.Retrieve the customer's account information, including all their accounts and their current balances.
    /* if you give correct customer_id which has (fk) with account table will show details. if no
    customer_id in account table will show empty. if give wrong input of customer_id will show error.
     */
    public List<Account> getCustomerAccounts(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accounts;
    }

    //5.View the status of their loan or credit card application, if active or inActive.
    //getStatusAllActiveLoans.
    public List<Loan> getStatusAllActiveLoans() {

        return loanRepository.getStatusAllActiveLoans();
    }

    // getAllNotActiveLoans :-
    public List<Loan> getStatusAllInActiveLoans() {

        return loanRepository.getStatusAllInActiveLoans();
    }

    //getStatusAllActiveCreditCard.
 /*   public List<CreditCard> getStatusAllActiveCreditCard() {

        return creditCardRepository.getStatusAllActiveCreditCard();
    }*/

    // getAllNotActiveLoans :-
  /*  public List<CreditCard> getStatusAllInActiveCreditCard() {

        return creditCardRepository.getStatusAllInActiveCreditCard();
    } */

}
