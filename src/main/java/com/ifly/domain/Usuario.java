package com.ifly.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@Entity(name = "usuario")
@Inheritance(strategy = JOINED)
@Data
public class Usuario extends GenericEntity{
    @Id
    @SequenceGenerator(
            name = "usuario_seq",
            sequenceName = "usuario_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "usuario_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "nome",
            columnDefinition = "varchar(100)"
    )
    private String nome;
    @Column(
            name = "login",
            columnDefinition = "varchar(320)",
            unique = true
    )
    private String login;
    @Column(
            name = "senha",
            columnDefinition = "varchar(50)"
    )
    private String senha;

    public Usuario(String nome,
                   String login,
                   String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

}
