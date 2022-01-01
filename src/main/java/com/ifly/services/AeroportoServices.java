package com.ifly.services;

import com.ifly.dto.AeroportoDTO;
import com.ifly.repositories.AeroportoRepository;

import java.util.List;

public class AeroportoServices {

    private AeroportoRepository aeroportoRepository = new AeroportoRepository();

    public List<AeroportoDTO> getAllAirports(){
        aeroportoRepository.openConnection();
        return AeroportoDTO.createDTOList(aeroportoRepository.getAllAirports());
    }

}
