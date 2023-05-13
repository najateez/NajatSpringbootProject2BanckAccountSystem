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

    }





