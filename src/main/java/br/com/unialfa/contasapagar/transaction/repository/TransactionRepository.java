package br.com.unialfa.contasapagar.transaction.repository;

import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t, ur.created_at FROM transactions t INNER JOIN user_releases ur ON ur.transaction.id = t.id AND ur.id = (SELECT MAX(ur2.id) FROM user_releases ur2 WHERE ur2.user.id = ?1 AND ur2.transaction.id = t.id)")
    List<Transaction> listTransactions(Long idUsuario);

}