package com.ifly.api.controllers;

import com.ifly.dto.VooDTO;
import com.ifly.services.VooServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
        if(airline != 0)
            return ResponseEntity.ok(vooServices.getVoosByOriginAndDestinyAirlineAndDate(origin, destiny,airline, flightDate));
        else
            return ResponseEntity.ok(vooServices.getVoosByOriginAndDestinyAndDate(origin, destiny, flightDate));
    }

}
