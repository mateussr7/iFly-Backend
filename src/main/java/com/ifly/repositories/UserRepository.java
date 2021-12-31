package com.ifly.repositories;

import com.ifly.domain.Usuario;
import com.ifly.dto.*;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends BaseRepository{

    public LoginDTO loginService(CredentialsDTO credentials){
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
                "         LEFT JOIN administrador a ON a.id = u.id" +
                "WHERE u.login = 'pedro_mendes@iFly.com'";
        String type = "";
        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
            //    stm.setString(1, credentials.getEmail());
                ResultSet set = stm.executeQuery();
                System.out.println('a');
                while(set.next()){
                    type = set.getString("tipo");
                    Usuario usuario = new Usuario();
                    usuario.setLogin(set.getString("email"));
                    usuario.setId(set.getLong("idUsuario"));
                    usuario.setSenha(set.getString("senha"));
                    usuario.setNome(set.getString("nome"));
                    if(usuario.getSenha().equalsIgnoreCase(credentials.getPassword())){
                        if(type.equalsIgnoreCase("passageiro")){
                            user = new PassageiroDTO(usuario, set.getString("cpf"), set.getDouble("quilometragem_voada"));
                        }else if(type.equalsIgnoreCase("admin")){
                            user = new AdministradorDTO(usuario, set.getString("cpf"));
                        }else if(type.equalsIgnoreCase("empresa aerea")){
                            user = new EmpresaAereaDTO(usuario, set.getString("cnpj"), set.getLong("id_administrador"));
                        }
                        login.setUser(user);
                        login.setType(type);
                    }else{
                        throw new IllegalArgumentException("CREDENCIAIS INCORRETAS");
                    }
                }
            }else{
                openConnection();
            }
        }catch (SQLException e){

        }
        return login;
    }

    private String getQuery(String path){
        String query = null;
        try{
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader reader = new BufferedReader(fileReader);
            query = reader.readLine();
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

}
