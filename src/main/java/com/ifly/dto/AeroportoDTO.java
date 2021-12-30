package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Aeroporto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.wololo.geojson.Point;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class AeroportoDTO {
    private Long id;
    private String nome;
    private String codigo;
    private Point geom;

    public AeroportoDTO(Aeroporto entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.codigo = entity.getCodigo();
        //this.geom = (Point) GeometryHelper.convertJtsGeometryToGeoJson(entity.getPointLocation());
    }

    public Aeroporto toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Aeroporto.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<AeroportoDTO> createDTOList(List<Aeroporto> entityList){
        List<AeroportoDTO> dtoList = new ArrayList<>();
        for(Aeroporto aeroporto: entityList){
            dtoList.add(new AeroportoDTO(aeroporto));
        }
        return dtoList;
    }
}
