package com.example.linkedrhapplication.controller;

import com.example.linkedrhapplication.domain.Curso;
import com.example.linkedrhapplication.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = { "/api/curso" })
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> recuperaTodosCursos() throws SQLException {
        return ResponseEntity.ok(cursoService.recuperaTodosCursos());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> criarCurso(@RequestBody Curso curso) throws SQLException {
        int inserted = cursoService.criarCurso(curso);
        return inserted > 0 ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{codigo}")
    public ResponseEntity<HttpStatus> atualizarCurso(@RequestBody Curso curso, @PathVariable int codigo) throws SQLException {
        int updated = cursoService.atualizarCurso(curso, codigo);
        return updated > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/{codigo}")
    public void deletarCurso(@PathVariable int codigo) throws SQLException {
        cursoService.deletarCurso(codigo);
    }
}
