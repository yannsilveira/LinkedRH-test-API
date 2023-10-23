package com.example.linkedrhapplication.services;

import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.domain.TurmaParticipante;
import com.example.linkedrhapplication.domain.dto.TurmaParticipanteFuncionarioDTO;
import com.example.linkedrhapplication.repository.TurmaParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaParticipanteService {

    @Autowired
    private TurmaParticipanteRepository turmaParticipanteRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    public int adicionaParticipanteATurma(int codigoTurma, Funcionario funcionario) throws SQLException {
        Funcionario funcionarioSalvo = funcionarioService.registraFuncionario(funcionario);
        return turmaParticipanteRepository.adicionaParticipanteATurma(codigoTurma, funcionarioSalvo);
    }

    public void removeParticipanteDaTurma(int turmaCodigo, Funcionario funcionario) throws SQLException {
        turmaParticipanteRepository.removeParticipanteDaTurma(turmaCodigo, funcionario);
    }

    public void deletaTodasTurmasParticipantesAssociadasATurma(int codigo) throws SQLException {
        turmaParticipanteRepository.deletaTodasTurmasParticipantesAssociadasATurma(codigo);
    }

    public List<TurmaParticipante> recuperaTodasTurmasParticipantesPorCodigoTurma(int codigo) throws SQLException {
        return turmaParticipanteRepository.recuperaTodasTurmasParticipantesPorCodigoTurma(codigo);
    }

    public int criaAssociacaoTurmaParticipante(Turma savedTurma, Funcionario funcionario) throws SQLException {
        return turmaParticipanteRepository.createClassEmployeeAssociation(savedTurma, funcionario);
    }

    public int contaFuncionarioPorTurma(Turma turma) throws SQLException {
        return turmaParticipanteRepository.contaFuncionarioPorTurma(turma);
    }

    public void deletarTodasTurmasParticipanteAssociadasAoCurso(int codigo) throws SQLException {
        turmaParticipanteRepository.deletarTodasTurmasParticipanteAssociadasAoCurso(codigo);
    }

    public List<TurmaParticipanteFuncionarioDTO> recuperaTodosParticipantes() throws SQLException {
        List<TurmaParticipante> turmaParticipantes = turmaParticipanteRepository.recuperaTodosParticipantes();
        List<TurmaParticipanteFuncionarioDTO> turmaParticipanteFuncionarioDTOList = new ArrayList<>();
        turmaParticipantes.forEach(turmaParticipante -> {
            turmaParticipante.getFuncionarios().forEach(funcionario -> {
                TurmaParticipanteFuncionarioDTO turmaParticipanteFuncionarioDTO = new TurmaParticipanteFuncionarioDTO();
                turmaParticipanteFuncionarioDTO.setFuncionarioAssociado(funcionario);
                turmaParticipanteFuncionarioDTOList.add(turmaParticipanteFuncionarioDTO);
            });
        });
        return turmaParticipanteFuncionarioDTOList.stream().sorted(
                Comparator.comparing(func -> func.getFuncionarioAssociado().getNome())).collect(Collectors.toList());
    }
}
