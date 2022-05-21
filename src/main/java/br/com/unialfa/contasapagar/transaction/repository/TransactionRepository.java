package br.com.unialfa.contasapagar.transaction.repository;

import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
//    @Query("SELECT T.*, U.username " +
//            "FROM jean_moreira.transactions T inner join jean_moreira.user_releases UR " +
//            "on UR.transaction_id = T.id and UR.id =" +
//            "(SLECT max(UR2.id)")
//    List<Transaction> transactions();
}