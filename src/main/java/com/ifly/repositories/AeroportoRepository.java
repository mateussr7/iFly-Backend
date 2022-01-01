package com.ifly.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifly.domain.Aeroporto;
import org.locationtech.jts.geom.Geometry;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Geometry convertJsonToPoint(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Geometry geom = mapper.readValue(json, Geometry.class);
            return geom;
        }catch (JsonProcessingException e){
        }
        return null;
    }

}
