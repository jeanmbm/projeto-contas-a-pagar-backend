package br.com.unialfa.contasapagar.transaction.repository;

import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

//    @Query("SELECT t.* FROM transactions t INNER JOIN user_releases ur ON t.id = ur.transaction_id_id WHERE ur.user_id_id = ?43")
//    List<Transaction> ListTransactions();
}