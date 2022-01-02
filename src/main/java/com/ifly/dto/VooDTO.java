package com.ifly.dto;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Voo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class VooDTO {
    private Long id;
    private int capacidade;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp horario;
    private Float valor;
    private Long idEmpresaAerea;
    private Long idRota;
    private int ticketsDisponiveis;

    public VooDTO(Voo entity){
        this.id = entity.getId();
        this.capacidade = entity.getCapacidade();
        this.horario = entity.getHorario();
        this.valor = entity.getValor();
        this.idEmpresaAerea = entity.getEmpresaAerea().getId();
        this.idRota = entity.getRota().getId();
    }

    public Voo toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Voo.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        return mapper.writeValueAsString(this);
    }

    public static List<VooDTO> createDTOList(List<Voo> entityList){
        List<VooDTO> dtoList = new ArrayList<>();
        for(Voo voo: entityList){
            dtoList.add(new VooDTO(voo));
        }
        return dtoList;
    }
}
