package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Services;

import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Account;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Customer;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Models.Transactions;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.CustomerRepository;
import com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    //1.Create a new transaction for a specific account
    public void createANewTransactions (@RequestParam Double transactionAmount) {

        Transactions transactionsObj = new Transactions();
        transactionsObj.setTransactionAmount(transactionAmount);
        transactionsRepository.save(transactionsObj);
    }

    //2.Retrieve the details of a specific transaction.
    public Transactions getTransactionById(int transactionId) {
        return transactionsRepository.findById(transactionId);
    }

    //3.Retrieve all transactions for a specific account
    public List<Transactions> getTransactionsForAccount(Integer accountId) {
        return transactionsRepository.findByAccountId(accountId);
    }

    //4.Calculate the fees or charges associated with the transaction.
    /* you should give in postman transactionAmount more than zero then will multiply with 0.02
       and give result of fees. otherwise the result will be zero.
     */

    public double calculateTransactionFee(Transactions transaction) {

        double fee = 0.0;

        if (transaction.getTransactionAmount() > 0) {
            fee = Math.abs(transaction.getTransactionAmount()) * 0.02;
        }

        return fee;
    }

    //5.Generate a report of all transactions within a specific time period.
    /* these startDate and endDate is for transactionsDate in db.
        exp:
        if i give in postman localhost:8080/transactions/generateReport?startDate=1990-11-11&endDate=1997-12-12 ,
        should print full details information record from tables transactions,account,customer only which
        has transactionDate between 1990-11-11 and 1997-12-12 .
     */
    public List<Transactions> findAllByTransactionDateBetween(Date startDate, Date endDate) {
        return transactionsRepository.findAllByTransactionDateBetween(startDate, endDate);
    }

    //6.Reverse a transaction in case of an error.
    public Transactions reverseTransaction(Integer transactionId) throws Exception {

        //there are many transactions, from these all transactions choose one transactionId.
        Optional<Transactions> transactionOptional = transactionsRepository.findById(transactionId);

        if (!transactionOptional.isPresent()) {
            throw new Exception("Transaction not found");
        }

        //will take original full record information from db table transactions.
        Transactions originalTransaction = transactionOptional.get();

        // Create a new transaction with the opposite amount and direction
        Transactions reverseTransaction = new Transactions();
        //exp: if transactionsAmount in db table -> 55.2.  after reverse will be -55.2.
        // 55.2 money inside bank. -55.2 money withdraw.
        reverseTransaction.setAccount(originalTransaction.getAccount());
        reverseTransaction.setTransactionAmount(-originalTransaction.getTransactionAmount());

        // Save the reverse transaction
        return transactionsRepository.save(reverseTransaction);
    }

}
