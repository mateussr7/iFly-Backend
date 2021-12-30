package com.ifly.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor
@Entity(name = "rota")
@Data
public class Rota extends GenericEntity{
    @Id
    @SequenceGenerator(
            name = "rota_seq",
            sequenceName = "rota_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "rota_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_aeroporto_partida")
    private Aeroporto aeroportoPartida;
    @ManyToOne
    @JoinColumn(name = "id_aeroporto_chegada")
    private Aeroporto aeroportoChegada;
    @Column(
            name = "distancia",
            columnDefinition = "double precision"
    )
    private Float distancia;
}
