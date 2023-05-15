package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long accountNumber;
    private Double balance;
    private String accountType; // accountType: can be Savings or Checking
    private Double interestRate; //i added this column to calculate interest (balance * interestRate)

 /*   @OneToMany(mappedBy = "account") //one account, many transactions
    private List<Transactions> transactions; */
    @ManyToOne //many accounts, one customer
    @JoinColumn(name= "customer_id" , referencedColumnName = "id")
    Customer customer;



/*  Another way solution:
   @ManyToOne //many Accounts, one customer
    @JoinColumn(name= "customer_id" , referencedColumnName = "id")
    Customer customer;
    @OneToMany(mappedBy = "account") //one account, many transactions
    private List<Transactions> transactions;
    @ManyToOne //many Accounts, one loan
    @JoinColumn(name= "loan_id" , referencedColumnName = "id")
    private Loan loan;
    @ManyToOne //many Accounts, one creditCard
    @JoinColumn(name= "creditCard_id" , referencedColumnName = "id")
    private CreditCard creditCard; */


}
