package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Data
public class MonthlyStatementForTheAccountDTO {

    private Date transactionDate;
    private Double balance;
    private Integer accountId;

    public MonthlyStatementForTheAccountDTO(Date transactionDate, Double balance, Integer accountId) {
        this.transactionDate = transactionDate;
        this.balance = balance;
        this.accountId = accountId;
    }
}
