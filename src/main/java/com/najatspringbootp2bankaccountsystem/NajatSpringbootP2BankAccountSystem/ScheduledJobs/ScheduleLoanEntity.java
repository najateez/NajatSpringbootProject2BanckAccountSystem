package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.ScheduledJobs;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.LoanService;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ScheduleLoanEntity {

    @Autowired
    LoanService loanService;
    @Autowired
    SlackClient slackClient;  //to which channel in slack you want to send data's.

 //   @Scheduled(cron = "* */5 * * * *")
 /*   @GetMapping("loan/calculateLoanInterest")
    public Loan calculateLoanInterest(@RequestParam Integer loanId) {
        Loan loanEntity= loanService.calculateLoanInterest(loanId);

        slackClient.sendMessage("--- LoanEntity fot this function: Calculate the interest on the loan and update the balance accordingly. send data from db to slack (api) ---");
        slackClient.sendMessage("Loan Amount:" + loanEntity.getLoanAmount());
        slackClient.sendMessage("Interest rate:" + loanEntity.getInterestRate());
        slackClient.sendMessage("school CreatedDate:" + loanEntity.getLoanAmount()+loanEntity.getInterestRate()+loanEntity.getLoanAmount()*loanEntity.getInterestRate());
        slackClient.sendMessage("school isActive:" + loanEntity.getActive());
        slackClient.sendMessage("school UpdatedDate:" + loanEntity.getUpdatedDate());

        return loanEntity;
    } */




}
