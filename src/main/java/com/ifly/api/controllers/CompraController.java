package com.ifly.api.controllers;

import com.ifly.dto.CompraDTO;
import com.ifly.services.CompraServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class CompraController {

    private CompraServices compraServices = new CompraServices();

    @GetMapping()
    public ResponseEntity<List<CompraDTO>> getAllByUser(@RequestBody Long idUser){
        return ResponseEntity.ok(compraServices.getAllFlightsPurchasesByUser(idUser));
    }

    @PostMapping("/delete")
    public ResponseEntity<CompraDTO> cancelPurchase(@RequestBody CompraDTO dto){
        return ResponseEntity.ok(compraServices.deletePurchase(dto));
    }

    @PostMapping("/new")
    public ResponseEntity<CompraDTO> insert(@RequestBody CompraDTO dto){
        CompraDTO compraDTO = compraServices.insertPurchase(dto);
        return compraDTO != null ? ResponseEntity.ok(compraDTO) : ResponseEntity.badRequest().build();
    }

}
