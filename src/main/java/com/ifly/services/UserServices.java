package com.ifly.services;

import com.ifly.domain.Passageiro;
import com.ifly.dto.CredentialsDTO;
import com.ifly.dto.LoginDTO;
import com.ifly.dto.PassageiroDTO;
import com.ifly.dto.UsuarioDTO;
import com.ifly.repositories.UserRepository;

import java.util.List;

public class UserServices {

    private UserRepository userRepository = new UserRepository();

    public LoginDTO login(CredentialsDTO credentials) throws IllegalAccessException {
        userRepository.openConnection();
        LoginDTO login = userRepository.getLoginByEmail(credentials.getEmail());
        if(!login.getUser().getSenha().equalsIgnoreCase(credentials.getPassword()))
            throw new IllegalAccessException("As credenciais informadas nao conferem");
        return login;
    }

    public PassageiroDTO registerNewPassenger(PassageiroDTO dto){
        userRepository.openConnection();
        Passageiro passageiro = userRepository.insertNewPassenger(dto);
        return new PassageiroDTO(passageiro);
    }

    public List<PassageiroDTO> getPassengerRanking(){
        userRepository.openConnection();
        List<Passageiro> passageiros = userRepository.getPassengerRanking();
        return PassageiroDTO.createDTOList(passageiros);
    }

}
