package com.ifly.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "administrador")
@PrimaryKeyJoinColumn(name = "id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Administrador extends Usuario{
    @Column(
            name = "cpf",
            columnDefinition = "varchar(11)",
            unique = true,
            nullable = false
    )
    private String cpf;

}
