package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double transactionAmount;
    private LocalDate transactionDate;

    @ManyToOne //many transactions, one account
    @JoinColumn(name= "account_id" , referencedColumnName = "id")
    Account account;








  /* Another way solution:
   @ManyToOne //many transactions, one account
    @JoinColumn(name= "account_id" , referencedColumnName = "id")
    Account account; */
}
