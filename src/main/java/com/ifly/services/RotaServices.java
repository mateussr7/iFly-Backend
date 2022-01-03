package com.ifly.services;

import com.ifly.domain.Rota;
import com.ifly.dto.RotaDTO;
import com.ifly.repositories.RotaRepository;
import com.ifly.utils.OrigDestRota;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class RotaServices {

    RotaRepository rotaRepository = new RotaRepository();

    public OrigDestRota getOrigDestByRotaId(Long id){
        rotaRepository.openConnection();
        return  rotaRepository.getOrigDestByRotaId(id);
    }
    public Long getIdRotaByOriginAndDestiny(int idOrigin, int idDestiny){
        rotaRepository.openConnection();
        Long idRota;
        idRota = rotaRepository.getIdRotaByOriginAndDestiny(idOrigin, idDestiny);
        if(idRota == 0)
            idRota = rotaRepository.insertRotaByOriginAndDestinyReturningId(idOrigin, idDestiny);
        return idRota;
    }



}
