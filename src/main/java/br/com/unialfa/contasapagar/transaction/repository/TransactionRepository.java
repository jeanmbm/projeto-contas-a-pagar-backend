package br.com.unialfa.contasapagar.transaction.repository;

import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
