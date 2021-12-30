package com.ifly.dto;

import com.ifly.domain.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class UsuarioDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;

    public UsuarioDTO(Usuario entity){
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.nome = entity.getNome();
        this.senha = entity.getSenha();
    }
}
