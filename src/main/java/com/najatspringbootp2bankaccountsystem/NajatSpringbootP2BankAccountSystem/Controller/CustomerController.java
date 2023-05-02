package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CreditCardService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // Create a new customer with their personal information.
    //exp: localhost:8080/customer/createANewCustomer?name=alkhatri&phoneNumber=99999999&email=alkhatri@b.com
    @RequestMapping(value = "customer/createANewCustomer", method = RequestMethod.POST)
    public void createANewCustomer(@RequestParam String name, @RequestParam Integer phoneNumber, @RequestParam String email) {

        customerService.createANewCustomer(name,phoneNumber,email);
    }

    //Update the customer information, such as their email or phone number.
    //exp: localhost:8080/customer/getUpdateCustomerById?name=aseel&phoneNumber=91457899&email=aseel@b.com&id=2
    @RequestMapping(value = "customer/getUpdateCustomerById", method = RequestMethod.POST)
    public void getUpdateCustomerById(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer phoneNumber ,@RequestParam String email) {
        customerService.getUpdateCustomerById(id,name,phoneNumber,email);
    }
}
