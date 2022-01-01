package com.ifly.repositories;

import com.ifly.domain.Passageiro;
import com.ifly.domain.Usuario;
import com.ifly.dto.*;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends BaseRepository{

    public LoginDTO getLoginByEmail(String email){
        UsuarioDTO user = null;
        LoginDTO login = new LoginDTO();
        String sql =
                "SELECT *, u.id AS idUsuario, " +
                "            CASE" +
                "               WHEN p.id is not null then 'passageiro'" +
                "               WHEN ea.id is not null then 'empresa aerea'" +
                "               WHEN a.id is not null then 'admin'" +
                "            END AS tipo" +
                "         FROM usuario u" +
                "         LEFT JOIN passageiro p ON p.id = u.id" +
                "         LEFT JOIN empresa_aerea ea ON ea.id = u.id" +
                "         LEFT JOIN administrador a ON a.id = u.id " +
                "WHERE u.login = ?";
        String type = "";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet set = stm.executeQuery();
            while(set.next()){
                type = set.getString("tipo");
                Usuario usuario = new Usuario();
                usuario.setLogin(set.getString("login"));
                usuario.setId(set.getLong("idUsuario"));
                usuario.setSenha(set.getString("senha"));
                usuario.setNome(set.getString("nome"));
                if(type.equalsIgnoreCase("passageiro")){
                    user = new PassageiroDTO(usuario, set.getString("cpf"), set.getDouble("quilometragem_voada"));
                }else if(type.equalsIgnoreCase("admin")){
                    user = new AdministradorDTO(usuario, set.getString("cpf"));
                }else if(type.equalsIgnoreCase("empresa aerea")){
                    user = new EmpresaAereaDTO(usuario, set.getString("cnpj"), set.getLong("id_administrador"));
                }
                login.setUser(user);
                login.setType(type);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return login;
    }

    public Passageiro insertNewPassenger(PassageiroDTO dto){
        String sql = "INSERT INTO usuario(nome, login, senha) VALUES(?, ?, ?)";
        String find = "SELECT id FROM usuario WHERE login = ?";
        String insertion = "INSERT INTO passageiro(id, cpf, quilometragem_voada) VALUES(?, ?, ?)";
        Passageiro passageiro = dto.toEntity();
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, dto.getNome());
            stm.setString(2, dto.getLogin());
            stm.setString(3, dto.getSenha());
            stm.executeUpdate();

            PreparedStatement finder = connection.prepareStatement(find);
            finder.setString(1, dto.getLogin());
            ResultSet rs = finder.executeQuery();
            Long idUser = null;
            while(rs.next())
                idUser = rs.getLong("id");

            PreparedStatement inserter = connection.prepareStatement(insertion);
            inserter.setLong(1, idUser);
            inserter.setString(2, dto.getCpf());
            inserter.setDouble(3, 0.0);
            inserter.executeUpdate();
            passageiro.setId(idUser);
            passageiro.setQuilometragemVoada(0.0);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return passageiro;
    }

    public List<Passageiro> getPassengerRanking(){
        String sql = "SELECT u.id as idUsuario, * FROM usuario u INNER JOIN passageiro p ON u.id = p.id ORDER BY p.quilometragem_voada DESC";
        List<Passageiro> passageiros = new ArrayList<>();

        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Passageiro passageiro = new Passageiro();
                passageiro.setId(rs.getLong("idUsuario"));
                passageiro.setLogin(rs.getString("login"));
                passageiro.setSenha(rs.getString("senha"));
                passageiro.setNome(rs.getString("nome"));
                passageiro.setCpf(rs.getString("cpf"));
                passageiro.setQuilometragemVoada(rs.getDouble("quilometragem_voada"));
                passageiros.add(passageiro);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return passageiros;
    }




}
