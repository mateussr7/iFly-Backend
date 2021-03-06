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
    public ResponseEntity<List<CompraDTO>> getAllByUser(@RequestParam Long idUser){
        return ResponseEntity.ok(compraServices.getAllFlightsPurchasesByUser(idUser));
    }

    @PostMapping("/delete")
    public ResponseEntity<CompraDTO> cancelPurchase(@RequestBody CompraDTO dto){
        return dto!=null ? ResponseEntity.ok(compraServices.deletePurchase(dto)): ResponseEntity.badRequest().build();
    }

    @PostMapping("/new")
    public ResponseEntity<CompraDTO> insert(@RequestBody CompraDTO dto){
        CompraDTO compraDTO = compraServices.insertPurchase(dto);
        return compraDTO != null ? ResponseEntity.ok(compraDTO) : ResponseEntity.badRequest().build();
    }

}
