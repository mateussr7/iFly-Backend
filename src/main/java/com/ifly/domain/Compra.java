package com.ifly.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifly.domain.composedPrimaryKeys.CompraKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity(name = "compra")
@Data
@IdClass(CompraKey.class)
public class Compra extends GenericEntity{

    @Id
    private Long idPassageiro;
    @Id
    private Long idVoo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
