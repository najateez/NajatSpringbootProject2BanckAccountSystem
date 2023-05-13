package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
public class AllTransactionsWithTimePeriodDTO {

    Integer id;
    Double transactionAmount;
    Date transactionDate;
    Integer accountId;
    Integer customerId;

    public AllTransactionsWithTimePeriodDTO(Integer id, Double transactionAmount, Date transactionDate, Integer accountId, Integer customerId) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.accountId = accountId;
        this.customerId = customerId;
    }
}
