package com.example.linkedrhapplication.controller;

import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.domain.TurmaParticipante;
import com.example.linkedrhapplication.domain.dto.TurmaParticipanteFuncionarioDTO;
import com.example.linkedrhapplication.services.TurmaParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Recupera todos os alunos matriculados em cursos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos recuperados com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao recuperar os alunos matriculados")})
    @GetMapping
    public ResponseEntity<List<TurmaParticipanteFuncionarioDTO>> recuperaTodosParticipantes() throws SQLException {
        return ResponseEntity.ok(turmaParticipanteService.recuperaTodosParticipantes());
    }

    @Operation(summary = "Matricula um aluno novo a uma turma", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno matriculado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao matricular um aluno à uma turma")})
    @PostMapping(value = "/{turmaCodigo}")
    public ResponseEntity<HttpStatus> adicionaParticipantesDaTurma(@PathVariable int turmaCodigo, @RequestBody Funcionario funcionario) throws SQLException {
        return turmaParticipanteService.adicionaParticipanteATurma(turmaCodigo, funcionario) > 0 ?
                new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Desmatricula um aluno novo a uma turma", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desmatrícula realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a desmatrícula de um aluno")})
    @DeleteMapping(value = "/{turmaCodigo}")
    public void removeParticipantesDaTurma(@PathVariable int turmaCodigo, @RequestBody Funcionario funcionario) throws SQLException {
        turmaParticipanteService.removeParticipanteDaTurma(turmaCodigo, funcionario);
    }
}
