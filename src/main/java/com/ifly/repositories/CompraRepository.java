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
        }catch (SQLException e){
            e.printStackTrace();
        }

        return dto;
    }

    public Compra insertNewPurchase(CompraDTO dto){
        String sql = "INSERT INTO compra VALUES(?, ?, ?, ?)";
        LocalDateTime dateTime;
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Compra compra = dto.toEntity();
        compra.setData(timestamp);
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, dto.getIdPassageiro());
            stm.setLong(2, dto.getIdVoo());
            stm.setTimestamp(3, timestamp);
            stm.setInt(4, dto.getAssento());
        }catch (SQLException e){
            e.printStackTrace();
        }

        return compra;
    }

}
