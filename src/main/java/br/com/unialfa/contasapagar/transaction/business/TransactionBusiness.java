package br.com.unialfa.contasapagar.transaction.business;

import br.com.unialfa.contasapagar.enuns.OperationType;
import br.com.unialfa.contasapagar.enuns.Status;
import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import br.com.unialfa.contasapagar.transaction.repository.TransactionRepository;
import br.com.unialfa.contasapagar.user.business.UserBusiness;
import br.com.unialfa.contasapagar.user.domain.User;
import br.com.unialfa.contasapagar.userReleases.business.UserReleasesBusiness;
import br.com.unialfa.contasapagar.userReleases.domain.UserReleases;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionBusiness {

    private final TransactionRepository transactionRepository;
    private final UserReleasesBusiness userReleasesBusiness;
    private final UserBusiness userBusiness;


    public TransactionBusiness(TransactionRepository transactionRepository, UserReleasesBusiness userReleasesBusiness, UserBusiness userBusiness) {
        this.transactionRepository = transactionRepository;
        this.userReleasesBusiness = userReleasesBusiness;
        this.userBusiness = userBusiness;
    }

    public ResponseEntity<?> registerTransaction(Transaction transaction, long userId) {
        // se o valor = 0, não realiza o registro da transação
        if(transaction.getValue() != 0) {
            try {
                Date date = new Date();
                transaction.setCreated_at(date);
                transaction.setStatus(Status.ACTIVE);
                Transaction transactionId = transactionRepository.save(transaction);

                Optional<User> user = userBusiness.findById(userId);

                UserReleases userReleases = new UserReleases();
                userReleases.setTransaction(transactionId);
                userReleases.setUser(user.get());
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

    public ResponseEntity<?> editTransaction(long id, Transaction transactionEdit, long userId) {
        if (transactionEdit.getValue() != 0) {
            try {
                Date date = new Date();
                return transactionRepository.findById(id).map(transaction -> {
                    transaction.setDescription(transactionEdit.getDescription());
                    transaction.setValue(transactionEdit.getValue());
                    transaction.setUpdated_at(date);
                    Transaction updated = transactionRepository.save(transaction);

                    Optional<User> user = userBusiness.findById(userId);

                    UserReleases userReleases = new UserReleases();
                    userReleases.setTransaction(updated);
                    userReleases.setUser(user.get());
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

    public ResponseEntity<?> cancelTransaction(long id, long userId) {
        try {
            Date date = new Date();
            return transactionRepository.findById(id)
                    .map(transaction -> {
                        transaction.setStatus(Status.INACTIVE);
                        transaction.setUpdated_at(date);
                        Transaction updated = transactionRepository.save(transaction);

                        Optional<User> user = userBusiness.findById(userId);

                        UserReleases userReleases = new UserReleases();
                        userReleases.setTransaction(updated);
                        userReleases.setUser(user.get());
                        userReleases.setCreated_at(date);
                        userReleases.setOperationType(OperationType.UPDATE);
                        userReleasesBusiness.registerUserReleases(userReleases);
                        return new ResponseEntity<>(HttpStatus.OK);
                    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<?> listTransactions(Long id) {
        return transactionRepository.listTransactions(id);
    }

}