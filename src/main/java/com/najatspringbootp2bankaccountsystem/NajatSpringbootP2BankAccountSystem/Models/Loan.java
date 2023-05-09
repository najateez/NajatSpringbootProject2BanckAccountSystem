package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double loanAmount;
    private Boolean status;
    private Double creditScore; //i added this column to solve this Question: Approve or reject a credit card application based on the customer's creditworthiness.
    private Double interestRate; //added to calculate interest of (loan entity Question)

    @ManyToOne //many loan, one customer
    @JoinColumn(name= "customer_id" , referencedColumnName = "id")
    Customer customer;



/*  Another way solution:
  @OneToMany(mappedBy = "loan") //one loan many accounts
    private List<Account> accounts; */
}
