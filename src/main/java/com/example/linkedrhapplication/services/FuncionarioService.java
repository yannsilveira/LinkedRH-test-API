package com.example.linkedrhapplication.services;

import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> recuperaTodosFuncionarios() throws SQLException {
        return funcionarioRepository.retrieveAllEmployees();
    }

    public Funcionario registraFuncionario(Funcionario funcionario) throws SQLException {
        return funcionarioRepository.registraFuncionario(funcionario);
    }
}
