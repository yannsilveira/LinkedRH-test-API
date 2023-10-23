package com.example.linkedrhapplication.repository;

import com.example.linkedrhapplication.configuration.DataSourceConfig;
import com.example.linkedrhapplication.domain.Funcionario;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioRepository {

    public List<Funcionario> retrieveAllEmployees() throws SQLException {
        BeanListHandler<Funcionario> beanListHandler = new BeanListHandler<>(Funcionario.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "SELECT * FROM funcionario";
        return runner.query(query, beanListHandler);
    }

    public Funcionario registraFuncionario(Funcionario funcionario) throws SQLException {
        BeanHandler<Funcionario> beanHandler = new BeanHandler<>(Funcionario.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "INSERT INTO funcionario (nome, cpf, nascimento, cargo, admissao, status) VALUES (?,?,?,?,?,?)";
        return runner.query(query, beanHandler, funcionario.getNome(), funcionario.getCpf(), funcionario.getNascimento(),
                funcionario.getCargo(), funcionario.getAdmissao(), funcionario.isStatus());
    }
}
