package com.ifly.repositories;

import com.ifly.domain.Administrador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorRepository extends BaseRepository{

    public Administrador getAdministradorById(Long id){
        String sql = "SELECT * FROM usuario u INNER JOIN administrador a ON a.id = u.id WHERE u.id = ?";
        Administrador administrador = new Administrador();

        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet set = stm.executeQuery();
            while(set.next()){
                administrador.setId(set.getLong("id"));
                administrador.setLogin(set.getString("nome"));
                administrador.setSenha(set.getString("senha"));
                administrador.setNome(set.getString("nome"));
                administrador.setCpf(set.getString("cpf"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return administrador;
    }

}
