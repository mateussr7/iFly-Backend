package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Rota;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class RotaDTO {
    private Long id;
    private Long idAeroportoPartida;
    private Long idAeroportoChegada;
    private Float distancia;

    public RotaDTO(Rota entity){
        this.id = entity.getId();
        this.idAeroportoPartida = entity.getAeroportoPartida().getId();
        this.idAeroportoChegada = entity.getAeroportoChegada().getId();
        this.distancia = entity.getDistancia();
    }
    public Rota toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Rota.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<RotaDTO> createDTOList(List<Rota> entityList){
        List<RotaDTO> dtoList = new ArrayList<>();
        for(Rota rota: entityList){
            dtoList.add(new RotaDTO(rota));
        }
        return dtoList;
    }
}
