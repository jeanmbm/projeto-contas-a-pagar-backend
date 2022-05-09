package br.com.unialfa.contasapagar.transaction.service;

import br.com.unialfa.contasapagar.transaction.business.TransactionBusiness;
import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController {

    private final TransactionBusiness transactionBusiness;

    public TransactionController(TransactionBusiness transactionBusiness) {
        this.transactionBusiness = transactionBusiness;
    }

    @PostMapping(path = "/add")
    public void registerTransaction(@RequestBody Transaction transaction) {
        transactionBusiness.registerTransaction(transaction);
    }

}
