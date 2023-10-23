package com.example.linkedrhapplication.controller;

import com.example.linkedrhapplication.domain.Curso;
import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.domain.dto.TurmaFuncionarioDTO;
import com.example.linkedrhapplication.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = { "/api/turma" })
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<HttpStatus> criarTurma(@RequestBody Turma turma, @RequestBody boolean criaTurmaComFuncionario) throws SQLException {
        int inserted = turmaService.criarTurma(turma, criaTurmaComFuncionario);
        return inserted > 0 ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{codigo}")
    public ResponseEntity<HttpStatus> atualizarTurma(@RequestBody Turma turma, @PathVariable int codigo) throws SQLException {
        int updated = turmaService.atualizarTurma(turma, codigo);
        return updated > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/{codigo}")
    public void deletarTurma(@PathVariable int codigo) throws SQLException {
        turmaService.deletarTurma(codigo);
    }

    @GetMapping(value = "/{codigoCurso}")
    public ResponseEntity<List<TurmaFuncionarioDTO>> recuperarTodasTurmasPorCurso(@PathVariable int codigoCurso) throws SQLException {
        return ResponseEntity.ok(turmaService.encontrarTodasTurmasPorCodigoCurso(codigoCurso));
    }
}
