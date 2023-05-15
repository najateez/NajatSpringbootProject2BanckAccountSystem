package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository //-> to retrieve customer data from the database
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //3. Update the customer information, such as their email or phone number.
    @Modifying
    @Transactional
    @Query(value = "UPDATE Customer s SET phoneNumber=:phoneNumber, email=:email WHERE s.id =:id")
    void updateCustomerEmailOrPhoneById(@Param("id") Integer id,@Param("phoneNumber") Integer phoneNumber, @Param("email") String email);
}



