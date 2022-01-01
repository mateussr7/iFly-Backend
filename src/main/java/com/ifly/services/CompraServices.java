package com.ifly.services;

import com.ifly.domain.Compra;
import com.ifly.dto.CompraDTO;
import com.ifly.repositories.CompraRepository;

import java.util.List;

public class CompraServices {

    private CompraRepository compraRepository = new CompraRepository();

    public List<CompraDTO> getAllFlightsPurchasesByUser(Long idUser){
        compraRepository.openConnection();
        List<Compra> compras = compraRepository.getAllPurchasesByUser(idUser);
        return CompraDTO.createDTOList(compras);
    }

    public CompraDTO deletePurchase(CompraDTO dto){
        compraRepository.openConnection();
        CompraDTO compra = compraRepository.cancelPurchase(dto);
        return compra;
    }

    public CompraDTO insertPurchase(CompraDTO dto){
        compraRepository.openConnection();
        Compra compra = compraRepository.insertNewPurchase(dto);
        return new CompraDTO(compra);
    }

}
