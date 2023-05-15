package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.DTO.AllLoanBalancesAndPaymentsDTO;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.DTO.AllTransactionsWithTimePeriodDTO;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.DTO.MonthlyStatementForTheAccountDTO;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Loan;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.AccountRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.LoanRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class JasperReportService {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;

    public static final String pathToReports = "C:\\Users\\Acer\\intellijIdea-workspace\\NajatSpringbootP2BankAccountSystem\\JasperReports";

    public String generateTransactionsReport(Date startDate, Date endDate) throws FileNotFoundException, JRException {

        List<Transactions> transactionsList = transactionsRepository.findAllByTransactionDateBetween(startDate, endDate);
        List<AllTransactionsWithTimePeriodDTO> transactionsDTOData = new ArrayList<>();

        for (Transactions transaction : transactionsList) {
            AllTransactionsWithTimePeriodDTO dto = new AllTransactionsWithTimePeriodDTO(
                    transaction.getId(),
                    transaction.getTransactionAmount(),
                    transaction.getTransactionDate(),
                    transaction.getAccount().getId(),
                    transaction.getAccount().getCustomer().getId()
            );
            transactionsDTOData.add(dto);
        }

        File file = ResourceUtils.getFile("classpath: AllTransactionsWithinASpecificTimePeriod.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactionsDTOData);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "Najat Tech Mahindra");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,paramters , dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports+"\\AllTransactionsWithinASpecificTimePeriod_ForTransactionsEntity.pdf");
        return "Report generated : " + pathToReports+"\\AllTransactionsWithinASpecificTimePeriod_ForTransactionsEntity.pdf";
    }

 //----------------------------------------------------------------------------------

 //AllLoanBalancesAndPayments Report:-

 public static final String pathToReports1 = "C:\\Users\\Acer\\intellijIdea-workspace\\NajatSpringbootP2BankAccountSystem\\JasperReports";

 public String generateAllLoanBalancesAndPaymentsReport() throws FileNotFoundException, JRException {
  List<Loan> loanList = loanRepository.findAll();  // to take data from db
  List<AllLoanBalancesAndPaymentsDTO> loanDTOData = new ArrayList<>();  //to store data from db table to (StudentDTO list of jaspersoft list).

  //to get all values from db
  for (Loan loan : loanList) {
   Integer id = loan.getId();
   Double loanAmount = loan.getLoanAmount();
   Boolean status = loan.getStatus();
   Double creditScore= loan.getCreditScore();
   Double interestRate= loan.getInterestRate();
   Integer customerId= loan.getCustomer().getId();

   //define object of contracture for property of DTO
   AllLoanBalancesAndPaymentsDTO loanDTOListObj = new AllLoanBalancesAndPaymentsDTO(id, loanAmount, status, creditScore, interestRate, customerId);
   //add this values from database to list of jasper report.
   loanDTOData.add(loanDTOListObj);
  }

  //add name of (jrxml) file.
  File file = ResourceUtils.getFile("classpath:AllLoanBalancesAndPaymentsReport_Jaspersoft.jrxml");
  JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
  JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(loanDTOData);
  Map<String, Object> paramters = new HashMap<>();
  paramters.put("CreatedBy", "Najat Tech Mahindra"); //created by any company name
  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramters, dataSource);
  // name given for pdf file when jasper report created.
  JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports1 + "\\AllLoanBalancesAndPaymentsReport_forLoanEntity.pdf");
  return "Report generated : " + pathToReports1 + "\\AllLoanBalancesAndPaymentsReport_forLoanEntity.pdf";
 }

    //------------------------------------------------------------

    //MonthlyStatementForTheAccount Report:-
    //take as input month and year from transaction_date and account_id from transactions table, will give result balance from account table.
    public static final String pathToReports2 = "C:\\Users\\Acer\\intellijIdea-workspace\\NajatSpringbootP2BankAccountSystem\\JasperReports";

    public String generateAMonthlyStatementForTheAccount(Integer accountId,Integer year,Integer month) throws FileNotFoundException, JRException {

        List<Transactions> transactionsList = accountRepository.generateAMonthlyStatementForTheAccount(accountId,year,month);
        List<MonthlyStatementForTheAccountDTO> monthlyStatementForTheAccountDTOData = new ArrayList<>();

        // Calculate the running balance for each transaction
        Double balance = 0.0;
        for (Transactions acc : transactionsList) {
            balance += acc.getAccount().getBalance();
            monthlyStatementForTheAccountDTOData.add(new MonthlyStatementForTheAccountDTO(acc.getTransactionDate(), balance, accountId));
        }

        File file = ResourceUtils.getFile("classpath:MonthlyStatementForTheAccountReport_Jaspersoft.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(monthlyStatementForTheAccountDTOData);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "Najat Tech Mahindra");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,paramters , dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports2+"\\MonthlyStatementForTheAccountReport_ForAccountEntity.pdf");
        return "Report generated : " + pathToReports2+"\\MonthlyStatementForTheAccountReport_ForAccountEntity.pdf";
    }



 }

