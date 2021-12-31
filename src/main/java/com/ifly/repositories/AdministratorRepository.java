package com.ifly.repositories;

import com.ifly.domain.Administrador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministratorRepository extends BaseRepository{

    public Administrador getAdministradorById(Long id){
        String sql = "SELECT * FROM usuario u INNER JOIN administrador a ON a.id = u.id WHERE u.id = ?";
        Administrador administrador = new Administrador();

        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
