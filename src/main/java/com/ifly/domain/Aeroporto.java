package com.ifly.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;


import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor
@Entity(name = "aeroporto")
@Data
public class Aeroporto extends GenericEntity{
    @Id
    @SequenceGenerator(
            name = "aeroporto_seq",
            sequenceName = "aeroporto_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "aeroporto_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "nome",
            columnDefinition = "varchar(255)"
    )
    private String nome;
    @Column(
            name = "codigo",
            columnDefinition = "varchar(10)",
            nullable = false
    )
    private String codigo;
    @Column(
            name = "geom",
            columnDefinition = "geometry"
    )
    private Geometry geom;
}
