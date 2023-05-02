package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.AccountRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    // Create a new account for a customer.
    public void createANewCustomer(@RequestParam String name, @RequestParam Integer phoneNumber, @RequestParam String email) {

        Customer customerObj = new Customer();
        customerObj.setName(name);
        customerObj.setPhoneNumber(phoneNumber);
        customerObj.setEmail(email);
        customerRepository.save(customerObj);
    }

    // Update the customer information, such as their email or phone number.
    public void getUpdateCustomerById(Integer id, String name, Integer phoneNumber, String email) {

        customerRepository.getUpdateCustomerById(id,name,phoneNumber,email);
    }
}
