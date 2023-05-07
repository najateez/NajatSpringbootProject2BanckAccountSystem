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
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private Long CreditCardNumber;
    private Boolean status;

    @ManyToOne //many creditcard, one customer
    @JoinColumn(name= "customer_id" , referencedColumnName = "id")
    Customer customer;





 /* Another way solution:
   @OneToMany(mappedBy = "creditCard")
    private List<Account> accounts; */
}
