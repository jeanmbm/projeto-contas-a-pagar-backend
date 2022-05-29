package br.com.unialfa.contasapagar.transaction.business;

import br.com.unialfa.contasapagar.enuns.OperationType;
import br.com.unialfa.contasapagar.enuns.Status;
import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import br.com.unialfa.contasapagar.transaction.repository.TransactionRepository;
import br.com.unialfa.contasapagar.userReleases.business.UserReleasesBusiness;
import br.com.unialfa.contasapagar.userReleases.domain.UserReleases;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionBusiness {

    private final TransactionRepository transactionRepository;
    public final UserReleasesBusiness userReleasesBusiness;

    public TransactionBusiness(TransactionRepository transactionRepository, UserReleasesBusiness userReleasesBusiness) {
        this.transactionRepository = transactionRepository;
        this.userReleasesBusiness = userReleasesBusiness;
    }

    public ResponseEntity<?> registerTransaction(Transaction transaction) {
        // se o valor = 0, não realiza o registro da transação
        if(transaction.getValue() != 0) {
            try {
                Date date = new Date();
                transaction.setCreated_at(date);
                transaction.setStatus(Status.ACTIVE);
                Transaction transactionId = transactionRepository.save(transaction);

                UserReleases userReleases = new UserReleases();
                userReleases.setTransactionId(transactionId);
                //userReleases.setUserId(idUser);
                userReleases.setCreated_at(date);
                userReleases.setOperationType(OperationType.CREATE);
                userReleasesBusiness.registerUserReleases(userReleases);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> editTransaction(long id, Transaction transactionEdit) {
        if (transactionEdit.getValue() != 0) {
            try {
                Date date = new Date();
                return transactionRepository.findById(id).map(transaction -> {
                    transaction.setDescription(transactionEdit.getDescription());
                    transaction.setValue(transactionEdit.getValue());
                    transaction.setUpdated_at(date);
                    Transaction updated = transactionRepository.save(transaction);

                    UserReleases userReleases = new UserReleases();
                    userReleases.setTransactionId(updated);
                    //userReleases.setUserId(idUser);
                    userReleases.setCreated_at(date);
                    userReleases.setOperationType(OperationType.UPDATE);
                    userReleasesBusiness.registerUserReleases(userReleases);

                    return new ResponseEntity<>(HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> cancelTransaction(long id) {
        Date date = new Date();
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setStatus(Status.INACTIVE);
                    transaction.setUpdated_at(date);
                    Transaction updated = transactionRepository.save(transaction);

                    UserReleases userReleases = new UserReleases();
                    userReleases.setTransactionId(updated);
                    //userReleases.setUserId(idUser);
                    userReleases.setCreated_at(date);
                    userReleases.setOperationType(OperationType.UPDATE);
                    userReleasesBusiness.registerUserReleases(userReleases);
                    return new ResponseEntity<>(HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Iterable<Transaction> listAll() {
        return transactionRepository.findAll();
    }

}