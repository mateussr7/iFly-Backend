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

    private VooRepository vooRepository = new VooRepository();

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
        }

        return dto;
    }

    public Compra insertNewPurchase(CompraDTO dto){

        List<Integer> occupiedSeats = vooRepository.getAllSeatsOccupied(dto.getIdVoo());
        int min = 9999;
        for(int i = 0; i<occupiedSeats.size(); i++){
            System.out.println("teste " + !occupiedSeats.contains(occupiedSeats.get(i)+1));
            if(!occupiedSeats.contains(occupiedSeats.get(i)+1)){
                if(min > occupiedSeats.get(i)+1)
                    min = occupiedSeats.get(i)+1;
                break;
            }
        }
        if(!occupiedSeats.contains(0))
            min = 0;

        if(min == 9999)
            throw new IllegalArgumentException();

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
            stm.setInt(4, min);
            stm.executeUpdate();
            updateQuilometragemVoada(dto, Boolean.FALSE);
        }catch (SQLException e){
            e.printStackTrace();
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
