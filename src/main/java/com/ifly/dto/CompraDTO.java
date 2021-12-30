package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Compra;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CompraDTO {
    private Long idPassageiro;
    private Long idVoo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp data;
    private int assento;

    public CompraDTO(Compra entity){
        this.idPassageiro = entity.getIdPassageiro();
        this.idVoo = entity.getIdVoo();
        this.data = entity.getData();
        this.assento = entity.getAssento();
    }
    public Compra toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Compra.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<CompraDTO> createDTOList(List<Compra> entityList){
        List<CompraDTO> dtoList = new ArrayList<>();
        for(Compra compra: entityList){
            dtoList.add(new CompraDTO(compra));
        }
        return dtoList;
    }
}
