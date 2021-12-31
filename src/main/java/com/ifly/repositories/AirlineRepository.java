package com.ifly.repositories;

import com.ifly.domain.EmpresaAerea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirlineRepository extends BaseRepository{

    private AdministratorRepository administratorRepository = new AdministratorRepository();

    public EmpresaAerea getEmpresaById(Long id){
        String sql = "SELECT * FROM usuario u INNER JOIN empresa_aerea ea ON ea.id = u.id WHERE ea.id = ?";
        EmpresaAerea empresaAerea = new EmpresaAerea();
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet set = stm.executeQuery();
            while(set.next()){
                empresaAerea.setId(set.getLong("id"));
                empresaAerea.setLogin(set.getString("login"));
                empresaAerea.setNome(set.getString("nome"));
                empresaAerea.setCnpj(set.getString("cnpj"));
                empresaAerea.setSenha(set.getString("senha"));
                empresaAerea.setAdministrador(administratorRepository.getAdministradorById(set.getLong("id_administrador")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return empresaAerea;
    }

}
