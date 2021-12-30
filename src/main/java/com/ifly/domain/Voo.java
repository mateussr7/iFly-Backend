package com.ifly.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor
@Entity(name = "voo")
@Data
public class Voo extends GenericEntity{

    @Id
    @SequenceGenerator(
            name = "voo_seq",
            sequenceName = "voo_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "voo_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "capacidade",
            columnDefinition = "integer"

    )
    private int capacidade;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(
            name = "horario",
            columnDefinition = "timestamp"
    )
    private Timestamp horario;
    @Column(
            name = "valor",
            columnDefinition = "double precision"
    )
    private Float valor;
    @ManyToOne
    @JoinColumn(name = "id_empresa_aerea", nullable = false)
    private EmpresaAerea empresaAerea;
    @ManyToOne
    @JoinColumn(name = "id_rota", nullable = false)
    private Rota rota;
}
