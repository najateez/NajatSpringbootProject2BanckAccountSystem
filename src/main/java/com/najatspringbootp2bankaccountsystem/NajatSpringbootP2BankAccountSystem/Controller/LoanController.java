package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.AccountRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.LoanRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CustomerService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;

    //1.Create a new loan application for a customer.
    //exp: localhost:8080/loan/createANewLoan?loanAmount=77.3
    @RequestMapping(value = "loan/createANewLoan", method = RequestMethod.POST)
    public void createANewLoan(@RequestParam Double loanAmount) {

        loanService.createANewLoan(loanAmount);
    }

    //3.Approve or reject a loan application based on the customer's creditworthiness.
    @PostMapping("loan/approveOrRejectLoanApplication")
    public void approveOrRejectLoanApplication(@RequestParam Integer customerId, @RequestParam Double creditScore, @RequestParam Double loanAmount) {
        loanService.approveOrRejectLoanApplication(customerId, creditScore, loanAmount);
    }

    //4.Calculate the interest on the loan and update the balance accordingly.
    //exp: localhost:8080/loan/calculateLoanInterest?loanId=2
    @GetMapping("loan/calculateLoanInterest")
    public Double calculateLoanInterest(@RequestParam Integer loanId) {
        return loanService.calculateLoanInterest(loanId);
    }

    //5.Allow the customer to make payments towards the loan balance.
    //exp: localhost:8080/loan/makePayment?loanId=2&paymentAmount=1.1
    /* it will subtract loan_amount column value which is in db table loan, depend of paymentAmount you
       gave in postman.
       so loan_amount will be updated.
     */
    @PostMapping("loan/makePayment")
    public ResponseEntity<String> makePayment(@RequestParam Integer loanId, @RequestParam Double paymentAmount) {
        loanService.makePayment(loanId, paymentAmount);
        return ResponseEntity.ok("Payment made successfully");
    }

    //6.Generate a report of all loan balances and payments.
    //will display all details from loan table with (fk) information of it customer, from customer table.
    @GetMapping("loan/GenerateAReportOfAllLoanBalancesAndPayments")
    public List<Loan> getAllLoans() {

        return loanService.getAllLoans();
    }
}

