package com.example.linkedrhapplication.services;

import com.example.linkedrhapplication.domain.Curso;
import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private TurmaParticipanteService turmaParticipanteService;

    public List<Curso> recuperaTodosCursos() throws SQLException {
        return cursoRepository.recuperaTodosCursos();
    }

    public int criarCurso(Curso curso) throws SQLException {
        return cursoRepository.criarCurso(curso);
    }

    public int atualizarCurso(Curso curso, int codigo) throws SQLException {
        return cursoRepository.atualizarCurso(curso, codigo);
    }

    public void deletarCurso(int codigo) throws SQLException {
        List<Turma> turmas = turmaService.checaSeCursoJaEstaAssociado(codigo);
        if (Objects.nonNull(turmas) && !turmas.isEmpty()) {
            turmaParticipanteService.deletarTodasTurmasParticipanteAssociadasAoCurso(codigo);
            turmaService.deletarTodasTurmarPorCurso(codigo);
            cursoRepository.deletarCurso(codigo);
         } else
             cursoRepository.deletarCurso(codigo);
    }
}
