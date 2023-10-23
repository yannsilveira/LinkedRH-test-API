package com.example.linkedrhapplication.controller;

import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.domain.TurmaParticipante;
import com.example.linkedrhapplication.domain.dto.TurmaParticipanteFuncionarioDTO;
import com.example.linkedrhapplication.services.TurmaParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = { "/api/turma-participante" })
public class TurmaParticipanteController {

    @Autowired
    private TurmaParticipanteService turmaParticipanteService;

    @GetMapping
    public ResponseEntity<List<TurmaParticipanteFuncionarioDTO>> recuperaTodosParticipantes() throws SQLException {
        return ResponseEntity.ok(turmaParticipanteService.recuperaTodosParticipantes());
    }

    @PostMapping(value = "/{turmaCodigo}")
    public ResponseEntity<HttpStatus> adicionaParticipantesDaTurma(@PathVariable int turmaCodigo, @RequestBody Funcionario funcionario) throws SQLException {
        return turmaParticipanteService.adicionaParticipanteATurma(turmaCodigo, funcionario) > 0 ?
                new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/{turmaCodigo}")
    public void removeParticipantesDaTurma(@PathVariable int turmaCodigo, @RequestBody Funcionario funcionario) throws SQLException {
        turmaParticipanteService.removeParticipanteDaTurma(turmaCodigo, funcionario);
    }
}
