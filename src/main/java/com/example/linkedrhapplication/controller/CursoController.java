package com.example.linkedrhapplication.controller;

import com.example.linkedrhapplication.domain.Curso;
import com.example.linkedrhapplication.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = { "/api/curso" })
@Tag(name = "curso")
@Slf4j
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Recupera todos os cursos cadastrados no banco", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos recuperados com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a recuperação dos cursos")})
    @GetMapping
    public ResponseEntity<List<Curso>> recuperaTodosCursos() throws SQLException {
        return ResponseEntity.ok(cursoService.recuperaTodosCursos());
    }

    @Operation(summary = "Realiza A criação de um curso", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar um curso")})
    @PostMapping
    public ResponseEntity<HttpStatus> criarCurso(@RequestBody Curso curso) throws SQLException {
        int inserted = cursoService.criarCurso(curso);
        return inserted > 0 ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Atualiza um curso previamente cadastrado no banco pelo seu código", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização de um curso realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar um curso")})
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<HttpStatus> atualizarCurso(@RequestBody Curso curso, @PathVariable int codigo) throws SQLException {
        int updated = cursoService.atualizarCurso(curso, codigo);
        return updated > 0 ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Realiza a exclusão de um curso", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar apagar um curso")})
    @DeleteMapping(value = "/{codigo}")
    public void deletarCurso(@PathVariable int codigo) throws SQLException {
        cursoService.deletarCurso(codigo);
    }
}
