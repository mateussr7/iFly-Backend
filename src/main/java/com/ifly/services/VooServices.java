package com.ifly.services;

import com.ifly.domain.Voo;
import com.ifly.dto.VooDTO;
import com.ifly.repositories.VooRepository;

import java.sql.Timestamp;

import java.util.List;

public class VooServices {
    VooRepository vooRepository = new VooRepository();

    public List<VooDTO> getVoosByOriginAndDestinyAndDate(int origin, int destiny, Timestamp date){
        vooRepository.openConnection();
        List<Voo> listVooEntity =  vooRepository.getVoosByOriginAndDestinyAndDate(origin, destiny, date);
        return VooDTO.createDTOList(listVooEntity);
    }

    public List<VooDTO> getVoosByOriginAndDestinyAirlineAndDate(int origin, int destiny,int airline, Timestamp date){
        vooRepository.openConnection();
        List<Voo> listVooEntity =  vooRepository.getVoosByOriginAndDestinyAirlineAndDate(origin, destiny,airline, date);
        return VooDTO.createDTOList(listVooEntity);
    }


}
