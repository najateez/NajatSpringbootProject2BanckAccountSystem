package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.LoanRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    CustomerRepository customerRepository;

    // 1.Create a new loan application for a customer
    public void createANewLoan (@RequestParam Double loanAmount) {

        Loan loanObj = new Loan();
        loanObj.setLoanAmount(loanAmount);
        loanRepository.save(loanObj);
    }

    // 4.Apply for a new loan or credit card
    public Loan applyForLoan(Integer customerId, Double loanAmount) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setLoanAmount(loanAmount);
        return loanRepository.save(loan);
    }
}
