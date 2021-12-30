package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Passageiro;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class PassageiroDTO extends UsuarioDTO{
    private String cpf;
    private Float quilometragemVoada;

    public PassageiroDTO(Passageiro entity){
        super(entity);
        this.cpf = entity.getCpf();
        this.quilometragemVoada = entity.getQuilometragemVoada();
    }
    public Passageiro toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Passageiro.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<PassageiroDTO> createDTOList(List<Passageiro> entityList){
        List<PassageiroDTO> dtoList = new ArrayList<>();
        for(Passageiro passageiro: entityList){
            dtoList.add(new PassageiroDTO(passageiro));
        }
        return dtoList;
    }
}
