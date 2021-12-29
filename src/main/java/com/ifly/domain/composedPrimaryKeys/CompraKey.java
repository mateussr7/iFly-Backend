package com.ifly.domain.composedPrimaryKeys;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import java.io.Serializable;

@EqualsAndHashCode
public class CompraKey implements Serializable {
    @Column(
            name = "passageiro_pkey",
            columnDefinition = "id"
    )
    protected Long idPassageiro;
    protected Long idVoo;

    public CompraKey(){}

    public CompraKey(Long idPassageiro, Long idVoo){
        this.idPassageiro = idPassageiro;
        this.idVoo = idVoo;
    }

}
