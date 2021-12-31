package com.ifly.services;

import com.ifly.domain.Voo;
import com.ifly.dto.VooDTO;
import com.ifly.repositories.VooRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VooServices {
    VooRepository vooRepository = new VooRepository();

    public List<VooDTO> getVoos(String origin, String destiny, Timestamp date){
        vooRepository.openConnection();
        ArrayList<Voo> listVooEntity =  vooRepository.getVooByOriginAndDestinyAndDate(origin, destiny, date);
        return VooDTO.createDTOList(listVooEntity);
    }
}
