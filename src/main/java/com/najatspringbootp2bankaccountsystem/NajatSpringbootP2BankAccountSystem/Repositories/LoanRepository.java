package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
}
