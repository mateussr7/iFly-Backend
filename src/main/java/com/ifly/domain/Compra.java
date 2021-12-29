package com.ifly.domain;

import com.ifly.domain.composedPrimaryKeys.CompraKey;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "compra")
@Data
@IdClass(CompraKey.class)
public class Compra extends GenericEntity{

    @Id
    private Long idPassageiro;
    @Id
    private Long idVoo;
    @Column(
            name = "data",
            columnDefinition = "timestamp"
    )
    private Timestamp data;
    @Column(
            name = "assento",
            columnDefinition = "integer"
    )
    private int assento;
}
