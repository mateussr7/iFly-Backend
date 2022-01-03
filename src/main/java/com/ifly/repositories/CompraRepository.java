package com.ifly.repositories;

import com.ifly.domain.Compra;
import com.ifly.dto.CompraDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraRepository extends BaseRepository{

    public List<Compra> getAllPurchasesByUser(Long idUser){
        String sql = "SELECT * FROM compra WHERE id_passageiro = ?";
        List<Compra> compras = new ArrayList<>();

        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, idUser);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Compra compra = new Compra();
                compra.setIdVoo(rs.getLong("id_voo"));
                compra.setAssento(rs.getInt("assento"));
                compra.setIdPassageiro(idUser);
                compra.setData(rs.getTimestamp("data"));
                compras.add(compra);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return compras;
    }

    public CompraDTO cancelPurchase(CompraDTO dto){
        String sql = "DELETE FROM compra WHERE id_passageiro = ? AND id_voo = ? AND assento = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, dto.getIdPassageiro());
            stm.setLong(2, dto.getIdVoo());
            stm.setInt(3, dto.getAssento());
            stm.executeUpdate();
            updateQuilometragemVoada(dto, Boolean.TRUE);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return dto;
    }

    public Compra insertNewPurchase(CompraDTO dto){
        String sql = "INSERT INTO compra VALUES(?, ?, ?, ?)";
        LocalDateTime dateTime;
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Compra compra = null;
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, dto.getIdPassageiro());
            stm.setLong(2, dto.getIdVoo());
            stm.setTimestamp(3, timestamp);
            stm.setInt(4, dto.getAssento());
            compra = dto.toEntity();
            compra.setData(timestamp);
            stm.executeUpdate();
            updateQuilometragemVoada(dto, Boolean.FALSE);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return compra;
    }

    private void updateQuilometragemVoada(CompraDTO dto, Boolean cancel){
        String sql;
        if(cancel) {
            sql = "UPDATE passageiro " +
                    "SET quilometragem_voada = quilometragem_voada - R.distancia " +
                    "FROM (SELECT * " +
                    "     FROM rota " +
                    "      WHERE rota.id = (SELECT id_rota " +
                    "                       FROM voo V " +
                    "                       WHERE V.id=?)) R " +
                    "WHERE passageiro.id = ?";
        }else {
            sql = "UPDATE passageiro " +
                    "SET quilometragem_voada = quilometragem_voada + R.distancia " +
                    "FROM (SELECT * " +
                    "     FROM rota " +
                    "      WHERE rota.id = (SELECT id_rota " +
                    "                       FROM voo V " +
                    "                       WHERE V.id=?)) R " +
                    "WHERE passageiro.id = ?";
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, dto.getIdVoo());
            stm.setLong(2, dto.getIdPassageiro());
            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return;
    }
}
