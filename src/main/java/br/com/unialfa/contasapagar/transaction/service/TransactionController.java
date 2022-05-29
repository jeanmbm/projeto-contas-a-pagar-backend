package br.com.unialfa.contasapagar.transaction.service;

import br.com.unialfa.contasapagar.transaction.business.TransactionBusiness;
import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import br.com.unialfa.contasapagar.user.domain.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController {

    private final TransactionBusiness transactionBusiness;

    public TransactionController(TransactionBusiness transactionBusiness) {
        this.transactionBusiness = transactionBusiness;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> registerTransaction(@RequestBody Transaction transaction) {
        return transactionBusiness.registerTransaction(transaction);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Transaction> listAll() {
        return transactionBusiness.listAll();
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<?> editTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
        return transactionBusiness.editTransaction(id ,transaction);
    }

    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancelTransaction(@PathVariable("id") long id) {
        return transactionBusiness.cancelTransaction(id);
    }

}