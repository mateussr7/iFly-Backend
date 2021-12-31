package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Administrador;
import com.ifly.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class AdministradorDTO extends UsuarioDTO{
    private String cpf;

    public AdministradorDTO(Administrador entity){
        super(entity);
        this.cpf = entity.getCpf();
    }

    public AdministradorDTO(Usuario entity, String cpf) {
        super(entity);
        this.cpf = cpf;
    }

    public Administrador toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Administrador.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<AdministradorDTO> createDTOList(List<Administrador> entityList){
        List<AdministradorDTO> dtoList = new ArrayList<>();
        for(Administrador administrador: entityList){
            dtoList.add(new AdministradorDTO(administrador));
        }
        return dtoList;
    }
}
