package com.example.linkedrhapplication.funcionario;

import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.services.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.*;

public class FuncionarioServiceTest {

    @Autowired
    private FuncionarioService funcionarioService;

    @Test
    public void registrarFuncionarioIncompletoEsperaRetornarErro() throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Alessandro");
        funcionario.setCargo("Consultor de vendas");

        assertThrows(RuntimeException.class, () -> funcionarioService.registraFuncionario(funcionario));
    }

    @Test
    public void registraFuncionarioCompletoEsperaSucesso() throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Alencar");
        funcionario.setCargo("Gerente de Vendas");
        funcionario.setAdmissao(new Date(2022, 10, 11));
        funcionario.setCpf("44444444444");
        funcionario.setNascimento(new Date(1994, 2, 9));
        funcionario.setStatus(true);

        Funcionario funcionarioSalvo = funcionarioService.registraFuncionario(funcionario);
        assertNotNull("O funcionario está sem dados", funcionarioSalvo);
    }

    @Test
    public void recuperaListaFuncionarios() throws SQLException {
        List<Funcionario> funcionarioList = funcionarioService.recuperaTodosFuncionarios();
        assertNotNull("A lista não pode estar vazia", funcionarioList);
        assertTrue("A lista deve conter pelo menos um funcionario", !funcionarioList.isEmpty());
        assertTrue("A lista deve conter de 1 a 11 funcionarios", funcionarioList.size() > 0 && funcionarioList.size() < 12);
    }
}
