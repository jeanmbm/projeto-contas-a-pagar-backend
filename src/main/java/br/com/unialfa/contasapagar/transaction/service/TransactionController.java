package br.com.unialfa.contasapagar.transaction.service;

import br.com.unialfa.contasapagar.transaction.business.TransactionBusiness;
import br.com.unialfa.contasapagar.transaction.domain.Transaction;
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

    @PostMapping(path = "/add/{userId}")
    public ResponseEntity<?> registerTransaction(@RequestBody Transaction transaction, @PathVariable("userId") long userId) {
        return transactionBusiness.registerTransaction(transaction, userId);
    }

    @GetMapping(path = "/list/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<?> listAll(@PathVariable("userId") Long userId) {
        return transactionBusiness.listTransactions(userId);
    }

    @PutMapping(value = "/edit/{id}/{userId}")
    public ResponseEntity<?> editTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction, @PathVariable("userId") long userId) {
        return transactionBusiness.editTransaction(id ,transaction, userId);
    }

    @PutMapping(value = "/cancel/{id}/{userId}")
    public ResponseEntity<?> cancelTransaction(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        return transactionBusiness.cancelTransaction(id, userId);
    }

}