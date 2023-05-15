package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Integer> {

    //5.View the status of their loan or credit card application, if active or inActive.
    //getStatusAllActiveCreditCardForACustomer
    @Query(value = "SELECT s from CreditCard s where s.status = true")
    List<CreditCard> getStatusAllActiveCreditCardForACustomer();

    //getStatusAllNotActiveCreditCard :-
    @Query(value = "SELECT s from CreditCard s where s.status = false")
    List<CreditCard> getStatusAllInActiveCreditCardForACustomer();

    //4.Allow the customer to make payments towards their credit card balance.
    @Transactional
    @Modifying
    @Query("UPDATE CreditCard l SET l.income = l.income - :paymentAmount WHERE l.id = :creditCardId")
    void makePayment(@Param("creditCardId") Integer creditCardId, @Param("paymentAmount") Double paymentAmount);

    //6.Generate a report of all credit card balances and payments.
    List<CreditCard> findAll();
}
