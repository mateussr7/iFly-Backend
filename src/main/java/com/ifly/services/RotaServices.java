package com.ifly.services;

import com.ifly.repositories.RotaRepository;

public class RotaServices {

    RotaRepository rotaRepository = new RotaRepository();

    public Long getIdRotaByOriginAndDestiny(int idOrigin, int idDestiny){
        rotaRepository.openConnection();
        Long idRota;
        idRota = rotaRepository.getIdRotaByOriginAndDestiny(idOrigin, idDestiny);
        if(idRota == 0)
            idRota = rotaRepository.insertRotaByOriginAndDestinyReturningId(idOrigin, idDestiny);
        return idRota;
    }



}
