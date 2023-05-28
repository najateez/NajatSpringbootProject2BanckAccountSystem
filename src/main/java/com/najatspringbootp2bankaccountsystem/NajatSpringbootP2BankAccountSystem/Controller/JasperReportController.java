package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Controller;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.repo.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@Component
@RestController
public class JasperReportController {

    @Autowired
    JasperReportService jasperReportService;

    //Generate a report of all transactions within a specific time period.
    @GetMapping("jasper/generateTransactionsReport")
    public ResponseEntity<String> generateTransactionsReport(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            String reportPath = jasperReportService.generateTransactionsReport(startDate, endDate);
            return ResponseEntity.ok(reportPath);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate report");
        }
    }

//-------------------------------------------------------------------------

        //AllLoanBalancesAndPayments Report:-
       //exp: localhost:8080/jasper/generateAllLoanBalancesAndPaymentsForLoanEntityReport
        @RequestMapping(value = "jasper/generateAllLoanBalancesAndPaymentsForLoanEntityReport", method = RequestMethod.GET)
        public void generateAllLoanBalancesAndPaymentsReport () throws Exception {
            jasperReportService.generateAllLoanBalancesAndPaymentsReport();
        }

    //-------------------------------------------------------------------------

    //exp: Generate a monthly statement for the account Report:-
    //take as input month and year from transaction_date and account_id from transactions table, will give result balance from account table.
    //exp: localhost:8080/jasper/findTransactionsByAccountAndMonth?id=2&year=1996&month=05
    @GetMapping("jasper/generateAMonthlyStatementForTheAccount")
    public ResponseEntity<String> generateAMonthlyStatementForTheAccount(
            @RequestParam("id") Integer accountId,
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {
        try {
            String reportPath = jasperReportService.generateAMonthlyStatementForTheAccount(accountId, year, month);
            return ResponseEntity.ok(reportPath);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    }





