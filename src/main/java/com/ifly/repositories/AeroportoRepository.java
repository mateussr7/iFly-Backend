package com.ifly.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Aeroporto;
import org.locationtech.jts.geom.Geometry;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeroportoRepository extends BaseRepository{

    public Aeroporto getAeroportoById(Long id){
        Aeroporto aeroporto = new Aeroporto();
        String sql = "SELECT id,nome,codigo,ST_AsGeoJSON(geom) as geom FROM aeroporto WHERE id = ?";
        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setLong(1,id);
                ResultSet set = stm.executeQuery();
                while(set.next()){
                    aeroporto.setCodigo(set.getString("codigo"));
                    aeroporto.setGeom(convertJsonToPoint(set.getString("geom")));
                    aeroporto.setId(set.getLong("id"));
                    aeroporto.setNome(set.getString("nome"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return aeroporto;
    }

    public List<Aeroporto> getAllAirports(){
        String sql = "SELECT id,nome,codigo,ST_AsGeoJSON(geom) as geom FROM aeroporto LIMIT 10";
        List<Aeroporto> airports = new ArrayList<>();

        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Aeroporto aeroporto = new Aeroporto();
                aeroporto.setCodigo(rs.getString("codigo"));
                aeroporto.setGeom(convertJsonToPoint(rs.getString("geom")));
                aeroporto.setId(rs.getLong("id"));
                aeroporto.setNome(rs.getString("nome"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return airports;
    }

    private Geometry convertJsonToPoint(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, Geometry.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

}
