package com.ifly.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity(name = "empresa_aerea")
@PrimaryKeyJoinColumn(name = "id")
@Data
@EqualsAndHashCode(callSuper = true)
public class EmpresaAerea extends Usuario{
    @Column(
            name = "cnpj",
            columnDefinition = "varchar(14)"
    )
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "id_administrador", nullable = false)
    private Administrador administrador;
}
