package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AllLoanBalancesAndPaymentsDTO {

     Integer id;
     Double loanAmount;
     Boolean status;
     Double creditScore;
     Double interestRate;
     Integer customerId;

    public AllLoanBalancesAndPaymentsDTO(Integer id, Double loanAmount, Boolean status, Double creditScore, Double interestRate, Integer customerId) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.status = status;
        this.creditScore = creditScore;
        this.interestRate = interestRate;
        this.customerId = customerId;
    }
}
