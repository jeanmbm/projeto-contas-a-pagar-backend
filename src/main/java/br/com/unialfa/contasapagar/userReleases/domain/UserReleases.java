package br.com.unialfa.contasapagar.userReleases.domain;

import br.com.unialfa.contasapagar.enuns.OperationType;
import br.com.unialfa.contasapagar.transaction.domain.Transaction;
import br.com.unialfa.contasapagar.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity(name = "user_releases")
public class UserReleases implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private Transaction transaction;
    private OperationType operationType;
    private Date created_at;
}