package com.ifly.repositories;

import com.ifly.domain.EmpresaAerea;
import com.ifly.domain.Voo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public EmpresaAerea updateEmpresaAerea(EmpresaAerea empresaAerea){
        String sql =
                "UPDATE usuario u SET" +
                        "   login = ?," +
                        "   senha = ?," +
                        "   nome = ?" +
                        "   WHERE u.id = ?";

        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, empresaAerea.getLogin());
                stm.setString(2, empresaAerea.getSenha());
                stm.setString(3, empresaAerea.getNome());
                stm.setLong(4, empresaAerea.getId());


                stm.executeUpdate();
                stm.close();
            }else{
                openConnection();
            }
        }catch (SQLException e){

        }
        return empresaAerea;
    }

    public EmpresaAerea insertEmpresaAerea(EmpresaAerea empresaAerea) {
        String sqlUser = "INSERT INTO usuario (login, senha, nome) VALUES (?, ?, ?) ";
        String sqlAirline =
                "INSERT INTO empresa_aerea (id, id_administrador, cnpj)" +
                "   VALUES ((select id from usuario where login = ?), ?, ?) ";

        try {
            if (connection.isValid(3)) {
                PreparedStatement stmUser = connection.prepareStatement(sqlUser);
                stmUser.setString(1, empresaAerea.getLogin());
                stmUser.setString(2, empresaAerea.getSenha());
                stmUser.setString(3, empresaAerea.getNome());

                PreparedStatement stmAirline = connection.prepareStatement(sqlAirline);
                stmAirline.setString(1, empresaAerea.getLogin());
                stmAirline.setLong(2, empresaAerea.getAdministrador().getId());
                stmAirline.setString(3, empresaAerea.getCnpj());

                stmUser.executeUpdate();
                stmAirline.executeUpdate();
                stmUser.close();
                stmAirline.close();
            } else {
                openConnection();
            }
        } catch (SQLException e) {

        }
        return empresaAerea;

    }

    public List<EmpresaAerea> getAllAirlines(){
        String sql = "SELECT u.id as idUsuario, * FROM usuario u INNER JOIN empresa_aerea ea ON u.id = ea.id";
        List<EmpresaAerea> airlines = new ArrayList<>();

        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                EmpresaAerea airline = new EmpresaAerea();
                airline.setId(rs.getLong("idUsuario"));
                airline.setNome(rs.getString("nome"));
                airline.setLogin(rs.getString("login"));
                airline.setSenha(rs.getString("senha"));
                airline.setCnpj(rs.getString("cnpj"));
                airline.setAdministrador(administratorRepository.getAdministradorById(rs.getLong("id_administrador")));
                airlines.add(airline);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return airlines;
    }

}
