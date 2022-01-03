package com.ifly.api.controllers;

import com.ifly.dto.VooDTO;
import com.ifly.services.VooServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/voo")
public class VooController {
    VooServices vooServices = new VooServices();

    @GetMapping
    public ResponseEntity<List<VooDTO>> getVoosByOriginAndDestinyAndDate(@RequestParam int origin,
                                                                         @RequestParam int destiny,
                                                                         @RequestParam (defaultValue ="0") int airline,
                                                                         @RequestParam String date){
        Timestamp flightDate = Timestamp.valueOf(date.concat(" 00:00:00"));
        List<VooDTO> list;
        if(airline != 0)
            list = vooServices.getVoosByOriginAndDestinyAirlineAndDate(origin, destiny,airline, flightDate);
        else
            list = vooServices.getVoosByOriginAndDestinyAndDate(origin, destiny, flightDate);
        for(VooDTO voo: list){
            voo.setTicketsDisponiveis(vooServices.getAllSeatsOccupiedByFlight(voo.getId()).size());
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public ResponseEntity<VooDTO> updateVoo(@RequestBody VooDTO vooDTO){
        VooDTO voo = vooServices.updateVoo(vooDTO);
        voo.setTicketsDisponiveis(vooServices.getAllSeatsOccupiedByFlight(voo.getId()).size());
        return voo!= null ? ResponseEntity.ok(voo) : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<VooDTO> insertVoo(@RequestBody VooDTO vooDTO){
        VooDTO result = vooServices.insertVoo(vooDTO);
        return result!= null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Integer>> getSeatsOccupied(@RequestBody Long idVoo){
        List<Integer> seats = vooServices.getAllSeatsOccupiedByFlight(idVoo);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/getVoosByUserId")
    public ResponseEntity<List<VooDTO>> getVoosByUserId(@RequestParam Long userId){
        List<VooDTO> voos = vooServices.getVoosByUserId(userId);
        for(VooDTO voo: voos){
            voo.setTicketsDisponiveis(vooServices.getAllSeatsOccupiedByFlight(voo.getId()).size());
        }
        return ResponseEntity.ok(voos);
    }

}
