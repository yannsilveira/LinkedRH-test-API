package com.example.linkedrhapplication.turma;

import com.example.linkedrhapplication.domain.Curso;
import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.domain.dto.TurmaFuncionarioDTO;
import com.example.linkedrhapplication.services.TurmaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TurmaServiceTest {

    @Autowired
    private TurmaService turmaService;

    @Test
    public void criaTurmaSemFuncionario() throws SQLException {
        Turma turma = new Turma();
        turma.setInicio(new Date(2024, 1, 1));
        turma.setFim(new Date(2024, 12, 15));
        turma.setLocal("Av. do Ipiranga, N°785");

        Curso curso = new Curso();
        curso.setCodigo(1);
        curso.setNome("Aprender Angular");
        curso.setDescricao("Aprendendo Angular 8 com state application");
        curso.setDuracao(80);

        turma.setCurso(curso);

        int turmaCriada = turmaService.criarTurma(turma, false);
        assertEquals("Turma criada com sucesso", turmaCriada == 1);
    }

    @Test
    public void criaTurmaComFuncionario() throws SQLException {
        Turma turma = new Turma();
        turma.setInicio(new Date(2025, 1, 1));
        turma.setFim(new Date(2025, 12, 15));
        turma.setLocal("Av. das Andorinhas, N°1000");

        Curso curso = new Curso();
        curso.setCodigo(2);
        curso.setNome("Aprender Angular");
        curso.setDescricao("Aprendendo Angular 11 com state application");
        curso.setDuracao(8200);

        turma.setCurso(curso);

        int turmaCriada = turmaService.criarTurma(turma, true);
        assertEquals("Turma criada com sucesso", turmaCriada == 1);
    }

    @Test
    public void deletaTodasTurmasPorCodigoCurso() {
        assertThrows(SQLException.class, () -> turmaService.deletarTodasTurmarPorCurso(1));
    }

    //Este teste espera uma lista vazia pois o teste anterior realiza a exclusão de todas as turmas do curso
    @Test
    public void recuperaTodasTurmasPorCodigoCursoEsperaEstarVazia() throws SQLException {
        List<TurmaFuncionarioDTO> turmaFuncionarioDTOList = turmaService.encontrarTodasTurmasPorCodigoCurso(1);
        assertEquals("A Lista está vazia", turmaFuncionarioDTOList.isEmpty());
    }
}
