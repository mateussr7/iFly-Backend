package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.EmpresaAerea;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class EmpresaAereaDTO extends UsuarioDTO {
    private String cnpj;
    private Long idAdministrador;

    public EmpresaAereaDTO(EmpresaAerea entity){
        super(entity);
        this.cnpj = entity.getCnpj();
        this.idAdministrador = entity.getAdministrador().getId();
    }

    public EmpresaAerea toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, EmpresaAerea.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<EmpresaAereaDTO> createDTOList(List<EmpresaAerea> entityList){
        List<EmpresaAereaDTO> dtoList = new ArrayList<>();
        for(EmpresaAerea empresaAerea: entityList){
            dtoList.add(new EmpresaAereaDTO(empresaAerea));
        }
        return dtoList;
    }
}
