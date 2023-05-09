package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

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
public interface LoanRepository extends CrudRepository<Loan, Integer> {

    //5.View the status of their loan or credit card application, if active or inActive.
    //getStatusAllActiveLoans
    @Query(value = "SELECT s from Loan s where s.status = true")
    List<Loan> getStatusAllActiveLoans();

    //getStatusAllNotActiveLoans :-
    @Query(value = "SELECT s from Loan s where s.status = false")
    List<Loan> getStatusAllInActiveLoans();

    //5.Allow the customer to make payments towards the loan balance.
    @Transactional
    @Modifying
    @Query("UPDATE Loan l SET l.loanAmount = l.loanAmount - :paymentAmount WHERE l.id = :loanId")
    void makePayment(@Param("loanId") Integer loanId, @Param("paymentAmount") Double paymentAmount);

    //6.Generate a report of all loan balances and payments.
    List<Loan> findAll();
}
