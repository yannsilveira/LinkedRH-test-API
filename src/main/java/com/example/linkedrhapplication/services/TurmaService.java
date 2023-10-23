package com.example.linkedrhapplication.services;

import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.domain.TurmaParticipante;
import com.example.linkedrhapplication.domain.dto.TurmaFuncionarioDTO;
import com.example.linkedrhapplication.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaParticipanteService turmaParticipanteService;

    @Autowired
    private FuncionarioService funcionarioService;

    public int criarTurma(Turma turma, boolean criaTurmaComFuncionario) throws SQLException {
        Turma savedTurma = turmaRepository.criarTurma(turma);
        if (criaTurmaComFuncionario) {
            TurmaParticipante turmaParticipante = new TurmaParticipante();
            List<Funcionario> funcionarios = funcionarioService.recuperaTodosFuncionarios();
            turmaParticipante.setTurma(savedTurma);
            turmaParticipante.setFuncionarios(funcionarios);
            AtomicInteger counter = new AtomicInteger();
            turmaParticipante.getFuncionarios().forEach(funcionario -> {
                try {
                    int classEmployeeAssociation = turmaParticipanteService.criaAssociacaoTurmaParticipante(savedTurma, funcionario);
                    counter.addAndGet(classEmployeeAssociation);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            return turmaParticipante.getFuncionarios().size() == counter.get() ? 1 : 0;
        } else
            return Objects.nonNull(savedTurma) ? 1 : 0;
    }

    public int atualizarTurma(Turma turma, int codigo) throws SQLException {
        return turmaRepository.atualizarTurma(turma, codigo);
    }

    public void deletarTurma(int codigo) throws SQLException {
        List<TurmaParticipante> turmaParticipantes = turmaParticipanteService.recuperaTodasTurmasParticipantesPorCodigoTurma(codigo);
        if (Objects.nonNull(turmaParticipantes) && !turmaParticipantes.isEmpty()) {
            turmaParticipanteService.deletaTodasTurmasParticipantesAssociadasATurma(codigo);
            turmaRepository.deletarTurma(codigo);
        } else
            turmaRepository.deletarTurma(codigo);
    }

    public List<TurmaFuncionarioDTO> encontrarTodasTurmasPorCodigoCurso(int codigoCurso) throws SQLException {
        List<Turma> turmas = turmaRepository.checaSeCursoJaEstaAssociado(codigoCurso);
        List<TurmaFuncionarioDTO> turmaFuncionarioDTOList = new ArrayList<>();
        turmas.forEach(turma -> {
            try {
                TurmaFuncionarioDTO turmaFuncionarioDTO = new TurmaFuncionarioDTO();
                int i = turmaParticipanteService.contaFuncionarioPorTurma(turma);
                turmaFuncionarioDTO.setTurma(turma);
                turmaFuncionarioDTO.setFuncionarioQuantidade(i);
                turmaFuncionarioDTOList.add(turmaFuncionarioDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return turmaFuncionarioDTOList;
    }

    public List<Turma> checaSeCursoJaEstaAssociado(int codigo) throws SQLException {
        return turmaRepository.checaSeCursoJaEstaAssociado(codigo);
    }

    public void deletarTodasTurmarPorCurso(int codigo) throws SQLException {
        turmaRepository.deletarTodasTurmarPorCurso(codigo);
    }
}
