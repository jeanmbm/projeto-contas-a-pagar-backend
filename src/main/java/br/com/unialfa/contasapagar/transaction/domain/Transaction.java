package br.com.unialfa.contasapagar.transaction.domain;

import br.com.unialfa.contasapagar.enuns.Status;
import br.com.unialfa.contasapagar.enuns.Type;
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

@Entity(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Type type;
    private String description;

    @Column(nullable = false)
    private double value;
    private Date updated_at;
    private Date created_at;
    private Status status;
}