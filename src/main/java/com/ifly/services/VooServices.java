package com.ifly.services;

import com.ifly.domain.EmpresaAerea;
import com.ifly.domain.Rota;
import com.ifly.domain.Voo;
import com.ifly.dto.VooDTO;
import com.ifly.repositories.AirlineRepository;
import com.ifly.repositories.RotaRepository;
import com.ifly.repositories.VooRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

import java.util.List;

public class VooServices {
    VooRepository vooRepository = new VooRepository();
    AirlineRepository airlineRepository = new AirlineRepository();
    RotaRepository rotaRepository = new RotaRepository();

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


    public VooDTO updateVoo(VooDTO vooDTO){
        vooRepository.openConnection();
        Voo vooEntity = new Voo();
        vooEntity.setHorario(vooDTO.getHorario());
        vooEntity.setId(vooDTO.getId());
        vooEntity.setValor(vooDTO.getValor());
        vooEntity.setCapacidade(vooDTO.getCapacidade());
        EmpresaAerea ea = airlineRepository.getEmpresaById(vooDTO.getIdEmpresaAerea());
        vooEntity.setEmpresaAerea(ea);
        Rota rota = rotaRepository.getRotaById(vooDTO.getIdRota());
        vooEntity.setRota(rota);
        vooEntity =  vooRepository.updateVoo(vooEntity);
        return new VooDTO(vooEntity);
    }

    public VooDTO insertVoo(VooDTO vooDTO) {
        vooRepository.openConnection();
        Voo vooEntity = new Voo();
        vooEntity.setHorario(vooDTO.getHorario());
        vooEntity.setId(vooDTO.getId());
        vooEntity.setValor(vooDTO.getValor());
        vooEntity.setCapacidade(vooDTO.getCapacidade());
        EmpresaAerea ea = airlineRepository.getEmpresaById(vooDTO.getIdEmpresaAerea());
        vooEntity.setEmpresaAerea(ea);
        Rota rota = rotaRepository.getRotaById(vooDTO.getIdRota());
        vooEntity.setRota(rota);
        vooEntity = vooRepository.insertVoo(vooEntity);
        return new VooDTO(vooEntity);
    }

    public List<Integer> getAllSeatsOccupiedByFlight(Long idVoo){
        vooRepository.openConnection();
        return vooRepository.getAllSeatsOccupied(idVoo);
    }

    public List<VooDTO> getVoosByUserId(Long userId){
        vooRepository.openConnection();
        return vooRepository.getVoosByUserId(userId);
    }

}
