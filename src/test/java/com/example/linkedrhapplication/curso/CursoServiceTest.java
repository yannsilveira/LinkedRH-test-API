package com.example.linkedrhapplication.curso;

import com.example.linkedrhapplication.domain.Curso;
import com.example.linkedrhapplication.services.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @Test
    public void recuperaTodosOsCursos() throws SQLException {
        List<Curso> cursos = cursoService.recuperaTodosCursos();
        assertNotNull("A lista não pode estar vazia", cursos);
        assertTrue("A lista deve conter pelo menos um curso", !cursos.isEmpty());
    }

    @Test
    public void registraUmCurso() throws SQLException {
        Curso curso = new Curso();
        curso.setNome("Banco de Dados");
        curso.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tincidunt neque vitae ultrices finibus. Morbi.");
        curso.setDuracao(180);//minutos, portanto 3 horas

        int cursoCriado = cursoService.criarCurso(curso);
        assertTrue("Curso criado com sucesso", cursoCriado == 1);
    }

    @Test
    public void atualizaDuracaoCurso() throws SQLException {
        Curso curso = new Curso();
        curso.setNome("Banco de Dados");
        curso.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tincidunt neque vitae ultrices finibus. Morbi.");
        curso.setDuracao(200);//minutos, portanto 3 horas e 20 min

        int cursoAtualizado = cursoService.atualizarCurso(curso, 1);
        assertTrue("O curso foi atualizado com sucesso", cursoAtualizado == 1);
    }
}
