package com.example.linkedrhapplication.controller;

import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.domain.dto.TurmaFuncionarioDTO;
import com.example.linkedrhapplication.services.TurmaService;
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
@RequestMapping(value = { "/api/turma" })
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Operation(summary = "Cria uma turma no banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma cadastrada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar uma turma")})
    @PostMapping
    public ResponseEntity<HttpStatus> criarTurma(@RequestBody Turma turma, @RequestBody boolean criaTurmaComFuncionario) throws SQLException {
        int inserted = turmaService.criarTurma(turma, criaTurmaComFuncionario);
        return inserted > 0 ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Atualiza uma turma previamente cadastrada no banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar uma turma")})
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<HttpStatus> atualizarTurma(@RequestBody Turma turma, @PathVariable int codigo) throws SQLException {
        int updated = turmaService.atualizarTurma(turma, codigo);
        return updated > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Deleta uma turma cadastrada no banco pelo código informado", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma apagada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar apagar uma turma")})
    @DeleteMapping(value = "/{codigo}")
    public void deletarTurma(@PathVariable int codigo) throws SQLException {
        turmaService.deletarTurma(codigo);
    }

    @Operation(summary = "Recupera todas as turmas por um curso específico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turmas recuperadas com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar recuperar todas turmas")})
    @GetMapping(value = "/{codigoCurso}")
    public ResponseEntity<List<TurmaFuncionarioDTO>> recuperarTodasTurmasPorCurso(@PathVariable int codigoCurso) throws SQLException {
        return ResponseEntity.ok(turmaService.encontrarTodasTurmasPorCodigoCurso(codigoCurso));
    }
}
