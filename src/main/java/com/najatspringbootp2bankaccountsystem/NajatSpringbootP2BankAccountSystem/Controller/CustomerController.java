package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.*;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.AccountService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CreditCardService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CustomerService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RestController
public class CustomerController { //-> to handle http request

    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    LoanService loanService;
    @Autowired
    CreditCardService creditCardService;

    //1.Create a new customer with their personal information.
    //exp: localhost:8080/customer/createANewCustomer?name=alkhatri&phoneNumber=99999999&email=alkhatri@b.com
    @RequestMapping(value = "customer/createANewCustomer", method = RequestMethod.POST)
    public void createANewCustomer(@RequestParam String name, @RequestParam Integer phoneNumber, @RequestParam String email) {

        customerService.createANewCustomer(name, phoneNumber, email);
    }

    //2.Update the customer information, such as their email or phone number.
    //exp: localhost:8080/customer/getUpdateCustomerById?name=aseel&phoneNumber=91457899&email=aseel@b.com&id=2
    @RequestMapping(value = "customer/getUpdateCustomerById", method = RequestMethod.POST)
    public void getUpdateCustomerById(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer phoneNumber, @RequestParam String email) {
        customerService.getUpdateCustomerById(id, name, phoneNumber, email);
    }

    //3.Retrieve the customer's account information, including all their accounts and their current balances.
    //exp: localhost:8080/customer/getCustomerAccounts?customerId=2
    //will give full record information about that customer from table customer and account.
    //used AccountRepository + customerService + CustomerController.
    @GetMapping("customer/getCustomerAccounts")
    public ResponseEntity<List<Account>> getCustomerAccounts(@RequestParam Integer customerId) {
        List<Account> accounts = customerService.getCustomerAccounts(customerId);
        return ResponseEntity.ok(accounts);
    }

    //4. Apply for a new loan or credit card
    /* exp: localhost:8080/customer/applyForLoan?customerId=3   , in Body of postman write: {"loanAmount":55.123}
        customerId should write same as in customer table, and loanAmount you can write any number to apply new
     */
    @PostMapping("customer/applyForLoan")
    public Loan applyForLoan(@RequestParam Integer customerId, @RequestBody Loan loan) {
        return loanService.applyForLoan(customerId, loan.getLoanAmount());
    }

    /* exp: localhost:8080/customer/applyForCreditCard?customerId=4   , in Body of postman write: {"creditCardNumber":789005}
        customerId should write same as in customer table, and creditCardNumber you can write any number to apply new
     */
    @PostMapping("customer/applyForCreditCard")
    public CreditCard applyForCreditCard(@RequestParam Integer customerId, @RequestBody CreditCard creditCard) {
        return creditCardService.applyForCreditCard(customerId, creditCard.getCreditCardNumber());
    }

    //5.View the status of their loan or credit card application. if active or inActive.
    //getStatusAllActiveLoans.
    @RequestMapping(value = "customer/getStatusAllActiveLoans")
    public List<Loan> getStatusAllActiveLoans() {
        List<Loan> activeLoanList = customerService.getStatusAllActiveLoans();
        return activeLoanList;
    }

    // getAllNotActiveLoans :-
    @RequestMapping(value = "customer/getStatusAllInActiveLoans")
    public List<Loan> getStatusAllInActiveLoans() {
        List<Loan> notActiveLoanList = customerService.getStatusAllInActiveLoans();
        return notActiveLoanList;
    }

 /*   @RequestMapping(value = "customer/getStatusAllActiveCreditCard")
    public List<CreditCard> getStatusAllActiveCreditCard() {
        List<CreditCard> activeCreditCardList = customerService.getStatusAllActiveCreditCard();
        return activeCreditCardList;
    }*/

    // getAllNotActiveLoans :-
/*    @RequestMapping(value = "customer/getStatusAllInActiveCreditCard")
    public List<CreditCard> getStatusAllInActiveCreditCard() {
        List<CreditCard> notActiveCreditCardList = customerService.getStatusAllInActiveCreditCard();
        return notActiveCreditCardList;
    }*/

}

