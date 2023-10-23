package com.example.linkedrhapplication.repository;

import com.example.linkedrhapplication.configuration.DataSourceConfig;
import com.example.linkedrhapplication.domain.Turma;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class TurmaRepository {

    public List<Turma> checaSeCursoJaEstaAssociado(int codigoCurso) throws SQLException {
        BeanListHandler<Turma> beanListHandler = new BeanListHandler<>(Turma.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "SELECT * FROM turma t LEFT JOIN curso c on t.curso = c.codigo WHERE c.codigo = ?" +
                " ORDER BY t.inicio ASC, t.fim ASC";
        return runner.insert(query, beanListHandler, codigoCurso);
    }

    public Turma criarTurma(Turma turma) throws SQLException {
        ResultSetHandler<Turma> resultSetHandler = new BeanHandler<>(Turma.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "INSERT INTO turma (inicio, fim, local, curso) VALUES(?,?,?,?)";
        return runner.query(query, resultSetHandler, turma.getInicio(), turma.getFim(),
                turma.getLocal(), turma.getCurso().getCodigo());
    }

    public int atualizarTurma(Turma turma, int codigo) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "UPDATE turma SET inicio = ?, fim = ?, local = ? WHERE codigo = ?";
        return runner.update(query, turma.getInicio(), turma.getFim(), turma.getLocal(), codigo);
    }

    public void deletarTurma(int codigo) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "DELETE FROM turma WHERE codigo = ?";
        int deletedRecord = runner.execute(query, codigo);
    }

    public void deletarTodasTurmarPorCurso(int cursoCodigo) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "DELETE t FROM turma t LEFT JOIN curso c on t.curso = c.codigo WHERE c.codigo = ? ";
        runner.execute(query, cursoCodigo);
    }
}
