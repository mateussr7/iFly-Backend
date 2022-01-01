package com.ifly.repositories;

import com.ifly.domain.Rota;
import com.ifly.domain.Usuario;
import com.ifly.dto.*;

import javax.xml.transform.Result;
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

    public Long getIdRotaByOriginAndDestiny(int idOrigin, int idDestiny){
        Long idRota = Long.valueOf(0);
        String sql = "SELECT * FROM ROTA WHERE id_aeroporto_partida = ? and id_aeroporto_chegada = ?";
        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setLong(1, idOrigin);
                stm.setLong(2, idDestiny);
                ResultSet set = stm.executeQuery();
                while(set.next()){
                    idRota = set.getLong("id");
                }
            }
        }catch (SQLException e){

        }
        return idRota;
    }

    public Long insertRotaByOriginAndDestinyReturningId(int idOrigin, int idDestiny){
        Long idRota = Long.valueOf(0);
        String sql = "INSERT INTO rota (id_aeroporto_partida, id_aeroporto_chegada, distancia)" +
                " VALUES (?, ?, " +
                "   ST_DistanceSphere(" +
                "       (select geom from aeroporto a where a.id = ?)," +
                "       (select geom from aeroporto a where a.id = ?)))";
        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setLong(1, idOrigin);
                stm.setLong(3, idOrigin);
                stm.setLong(2, idDestiny);
                stm.setLong(4, idDestiny);
                stm.executeUpdate();
                stm.close();

                PreparedStatement stm2= connection.prepareStatement("SELECT * FROM rota ORDER BY id desc");
                ResultSet set = stm2.executeQuery();

                if(set.next()){
                    idRota = Long.valueOf(set.getInt("id"));
                }
            }
        }catch (SQLException e){

        }
        return idRota;
    }

}
