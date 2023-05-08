package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CustomerService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class LoanController {

    @Autowired
    LoanService loanService;

    //1.Create a new loan application for a customer.
    //exp: localhost:8080/loan/createANewLoan?loanAmount=77.3
    @RequestMapping(value = "loan/createANewLoan", method = RequestMethod.POST)
    public void createANewLoan(@RequestParam Double loanAmount) {

        loanService.createANewLoan(loanAmount);
    }

    //3.Approve or reject a loan application based on the customer's creditworthiness.
    @PostMapping("loan/approveOrRejectLoanApplication")
    public void approveOrRejectLoanApplication(@RequestParam Integer customerId, @RequestParam Double creditScore, @RequestParam Double loanAmount) {
        loanService.approveOrRejectLoanApplication(customerId, creditScore,loanAmount);
    }
}
