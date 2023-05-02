package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double transactionAmount;
    @UpdateTimestamp
    private Date transactionDate;

    @ManyToOne //many transactions, one account
    @JoinColumn(name= "account_id" , referencedColumnName = "id")
    Account account;








  /* Another way solution:
   @ManyToOne //many transactions, one account
    @JoinColumn(name= "account_id" , referencedColumnName = "id")
    Account account; */
}
