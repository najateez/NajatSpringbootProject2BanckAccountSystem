package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.*;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.AccountService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CreditCardService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CustomerService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //exp: localhost:8080/customer/getUpdateCustomerById?phoneNumber=91457899&email=aseel@b.com&id=2
    @RequestMapping(value = "customer/updateCustomerEmailOrPhoneById", method = RequestMethod.POST)
    public void updateCustomerEmailOrPhoneById(@RequestParam Integer id, @RequestParam Integer phoneNumber, @RequestParam String email) {
        customerService.updateCustomerEmailOrPhoneById(id,phoneNumber, email);
    }

    //3.Retrieve the customer's account information, including all their accounts and their current balances.
    //exp: localhost:8080/customer/customersAccountInformationIncludingAllTheirAccountsAndBalances?customerId=1
    //will give full record information about that customer from table customer and account.
    //used AccountRepository + customerService + CustomerController.
    @GetMapping("customer/customersAccountInformationIncludingAllTheirAccountsAndBalances")
    public ResponseEntity<List<Account>> customersAccountInformation(@RequestParam Integer customerId) {
        List<Account> accounts = customerService.customersAccountInformation(customerId);
        return ResponseEntity.ok(accounts);
    }

    //4. Apply for a new loan or credit card. (for customer entity)
    /* exp: localhost:8080/customer/applyForANewLoan?customerId=4   , in Body of postman write: {"loanAmount":10.9}
        customerId should write same as in customer table, and loanAmount you can write any number to apply new
     */
    //LoanService + CustomerController
    @PostMapping("customer/applyForANewLoan")
    public Loan applyForANewLoan(@RequestParam Integer customerId, @RequestBody Loan loan) {
        return loanService.applyForANewLoan(customerId, loan.getLoanAmount());
    }

    /* exp: localhost:8080/customer/applyForCreditCard?customerId=4   , in Body of postman write: {"creditCardNumber":789005}
        customerId should write same as in customer table, and creditCardNumber you can write any number to apply new
     */
    @PostMapping("customer/applyForANewCreditCard")
    public CreditCard applyForANewCreditCard(@RequestParam Integer customerId, @RequestBody CreditCard creditCard) {
        return creditCardService.applyForANewCreditCard(customerId, creditCard.getCreditCardNumber());
    }

    //5.View the status of their loan or credit card application. if active or inActive. (for customer entity)
    //getStatusAllActiveLoansForACustomer.
    //exp: localhost:8080/customer/getStatusAllActiveLoansForACustomer
    //LoanRepository + CustomerService
    @RequestMapping(value = "customer/getStatusAllActiveLoansForACustomer")
    public List<Loan> getStatusAllActiveLoansForACustomer() {
        List<Loan> activeLoanList = customerService.getStatusAllActiveLoansForACustomer();
        return activeLoanList;
    }

    // getStatusAllNotActiveLoansForACustomer :-
    //exp: localhost:8080/customer/getStatusAllInActiveLoansForACustomer
    //LoanRepository + CustomerService
    @RequestMapping(value = "customer/getStatusAllInActiveLoansForACustomer")
    public List<Loan> getStatusAllInActiveLoansForACustomer() {
        List<Loan> notActiveLoanList = customerService.getStatusAllInActiveLoansForACustomer();
        return notActiveLoanList;
    }

    //getStatusAllActiveCreditCardForACustomer
    //exp: localhost:8080/customer/getStatusAllActiveCreditCardForACustomer
    //CreditCardRepository + CustomerService
    @RequestMapping(value = "customer/getStatusAllActiveCreditCardForACustomer")
    public List<CreditCard> getStatusAllActiveCreditCardForACustomer() {
        List<CreditCard> activeCreditCardList = customerService.getStatusAllActiveCreditCardForACustomer();
        return activeCreditCardList;
    }

    // getStatusAllNotActiveCreditCardsForACustomer :-
    //exp: localhost:8080/customer/getStatusAllInActiveCreditCardForACustomer
    //CreditCardRepository + CustomerService
    @RequestMapping(value = "customer/getStatusAllInActiveCreditCardForACustomer")
    public List<CreditCard> getStatusAllInActiveCreditCardForACustomer() {
        List<CreditCard> notActiveCreditCardList = customerService.getStatusAllInActiveCreditCardForACustomer();
        return notActiveCreditCardList;
    }

    //6.Retrieve the customer's transaction history across all their accounts.
    //exp: localhost:8080/customer/customersTransactionsHistoryAcrossAllTheirAccountsByAccountId?accountId=3
    /* you will write any customer id from transactions table, then will show full record information
       from transactions table, then account table, then customer table. (fk).
     */
    @GetMapping("customer/customersTransactionsHistoryAcrossAllTheirAccountsByAccountId")
    public List<Transactions> customersTransactionsHistoryAcrossAllTheirAccountsByAccountId(@RequestParam Integer accountId) {
        return customerService.customersTransactionsHistoryAcrossAllTheirAccountsByAccountId(accountId);
    }

}

