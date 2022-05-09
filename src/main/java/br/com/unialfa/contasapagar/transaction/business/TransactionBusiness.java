package br.com.unialfa.contasapagar.transaction.business;

import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import br.com.unialfa.contasapagar.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransactionBusiness {

    private final TransactionRepository transactionRepository;


    public TransactionBusiness(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void registerTransaction(Transaction transaction) {
        Date date = new Date();
        transaction.setCreated_at(date);
        transactionRepository.save(transaction);
    }

//    private String getDateTime() {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        return dateFormat.format(date);
//    }
}
