package br.com.unialfa.contasapagar.user.domain;

import br.com.unialfa.contasapagar.enuns.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

// Lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private Status status;

}