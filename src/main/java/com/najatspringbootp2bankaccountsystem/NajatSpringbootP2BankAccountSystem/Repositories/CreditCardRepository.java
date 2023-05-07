package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.CreditCard;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Integer> {

    //5.View the status of their loan or credit card application, if active or inActive.
    //getStatusAllActiveCreditCard
    @Query(value = "SELECT s from CreditCard s where s.status = true")
    List<CreditCard> getStatusAllActiveCreditCard();

    //getStatusAllNotActiveCreditCard :-
    @Query(value = "SELECT s from CreditCard s where s.status = false")
    List<CreditCard> getStatusAllInActiveCreditCard();

}
