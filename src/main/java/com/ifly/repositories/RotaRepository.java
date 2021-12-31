package com.ifly.repositories;

import com.ifly.domain.Rota;
import com.ifly.domain.Usuario;
import com.ifly.dto.*;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RotaRepository extends BaseRepository{

    AeroportoRepository aeroportoRepository = new AeroportoRepository();

    public Rota getRotaById(Long id){
        Rota rota = new Rota();
        String sql = "SELECT * FROM ROTA WHERE id = " + id;
        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet set = stm.executeQuery();
                while(set.next()){
                    rota.setId(set.getLong("id"));
                    rota.setAeroportoChegada(aeroportoRepository.getAeroportoById(
                            set.getLong("id_aeroporto_chegada")));
                    rota.setAeroportoPartida(aeroportoRepository.getAeroportoById(
                            set.getLong("id_aeroporto_partida")));
                    rota.setDistancia(set.getFloat("distancia"));
                }
            }
        }catch (SQLException e){

        }
        return rota;
    }

}
