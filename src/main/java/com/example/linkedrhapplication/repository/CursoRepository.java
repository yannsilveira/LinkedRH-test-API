package com.example.linkedrhapplication.repository;

import com.example.linkedrhapplication.configuration.DataSourceConfig;
import com.example.linkedrhapplication.domain.Curso;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CursoRepository {

    public List<Curso> recuperaTodosCursos() throws SQLException {
        BeanListHandler<Curso> beanListHandler = new BeanListHandler<>(Curso.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "SELECT * FROM curso c ORDER BY c.nome ASC";
        return runner.query(query, beanListHandler);
    }

    public int criarCurso(Curso curso) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "INSERT INTO curso (nome, descricao, duracao) VALUES(?,?,?)";
        return runner.update(query, curso.getNome(), curso.getDescricao(), curso.getDuracao());
    }

    public int atualizarCurso(Curso curso, int codigo) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "UPDATE curso SET nome = ?, descricao = ?, duracao = ? WHERE codigo = ?";
        return runner.update(query, curso.getNome(), curso.getDescricao(), curso.getDuracao(), codigo);
    }

    public void deletarCurso(int codigo) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "DELETE FROM curso WHERE codigo = ?";
        int deletedRecord = runner.execute(query, codigo);
    }
}
