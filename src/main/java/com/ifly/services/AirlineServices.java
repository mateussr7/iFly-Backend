package com.ifly.services;

import com.ifly.domain.EmpresaAerea;
import com.ifly.domain.Rota;
import com.ifly.domain.Voo;
import com.ifly.dto.EmpresaAereaDTO;
import com.ifly.dto.VooDTO;
import com.ifly.repositories.AdministratorRepository;
import com.ifly.repositories.AirlineRepository;

public class AirlineServices {

    private AirlineRepository airlineRepository = new AirlineRepository();
    private AdministratorRepository administratorRepository = new AdministratorRepository();

    public EmpresaAereaDTO updateEmpresaAerea(EmpresaAereaDTO empresaAereaDTO){
        airlineRepository.openConnection();
        EmpresaAerea empresaAereaEntity = new EmpresaAerea();
        empresaAereaEntity.setId(empresaAereaDTO.getId());
        empresaAereaEntity.setSenha(empresaAereaDTO.getSenha());
        empresaAereaEntity.setLogin(empresaAereaDTO.getLogin());
        empresaAereaEntity.setNome(empresaAereaDTO.getNome());
        empresaAereaEntity.setAdministrador(administratorRepository.
                getAdministradorById(empresaAereaDTO.getIdAdministrador()));
        empresaAereaEntity.setCnpj(empresaAereaDTO.getCnpj());
        empresaAereaEntity = airlineRepository.updateEmpresaAerea(empresaAereaEntity);
        return new EmpresaAereaDTO(empresaAereaEntity);
    }

    public EmpresaAereaDTO insertEmpresaAerea(EmpresaAereaDTO empresaAereaDTO) {
        airlineRepository.openConnection();
        EmpresaAerea empresaAereaEntity = new EmpresaAerea();
        empresaAereaEntity.setId(empresaAereaDTO.getId());
        empresaAereaEntity.setSenha(empresaAereaDTO.getSenha());
        empresaAereaEntity.setLogin(empresaAereaDTO.getLogin());
        empresaAereaEntity.setNome(empresaAereaDTO.getNome());
        empresaAereaEntity.setAdministrador(administratorRepository.
                getAdministradorById(empresaAereaDTO.getIdAdministrador()));
        empresaAereaEntity.setCnpj(empresaAereaDTO.getCnpj());
        empresaAereaEntity = airlineRepository.insertEmpresaAerea(empresaAereaEntity);
        return new EmpresaAereaDTO(empresaAereaEntity);
    }
}
