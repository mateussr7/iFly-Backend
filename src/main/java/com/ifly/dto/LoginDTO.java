package com.ifly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginDTO {
    private UsuarioDTO user;
    private String type;

    public LoginDTO(UsuarioDTO user, String type){
        this.user = user;
        this.type = type;
    }
}
