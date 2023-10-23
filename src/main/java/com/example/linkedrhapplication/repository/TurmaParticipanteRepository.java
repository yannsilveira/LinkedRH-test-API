package com.example.linkedrhapplication.repository;

import com.example.linkedrhapplication.configuration.DataSourceConfig;
import com.example.linkedrhapplication.domain.Funcionario;
import com.example.linkedrhapplication.domain.Turma;
import com.example.linkedrhapplication.domain.TurmaParticipante;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class TurmaParticipanteRepository {

    public int createClassEmployeeAssociation(Turma savedTurma, Funcionario funcionario) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "INSERT INTO turma_participante (turma,funcionario) VALUES (?,?)";
        return runner.update(query, savedTurma.getCodigo(), funcionario.getCodigo());
    }

    public void deletarTodasTurmasParticipanteAssociadasAoCurso(int codigoCurso) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "DELETE tp FROM turma_participante LEFT JOIN turma t on tp.turma = t.codigo LEFT JOIN" +
                " curso c on t.curso = c.codigo WHERE c.codigo = ? ";
        runner.execute(query, codigoCurso);
    }

    public void deletaTodasTurmasParticipantesAssociadasATurma(int codigoTurma) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "DELETE tp FROM turma_participante LEFT JOIN turma t on tp.turma = t.codigo WHERE t.codigo = ? ";
        runner.execute(query, codigoTurma);
    }

    public List<TurmaParticipante> recuperaTodasTurmasParticipantesPorCodigoTurma(int codigoTurma) throws SQLException {
        BeanListHandler<TurmaParticipante> beanListHandler = new BeanListHandler<>(TurmaParticipante.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "SELECT * FROM turma_participante tp WHERE tp.turma = ?";
        return runner.query(query, beanListHandler, codigoTurma);
    }

    public int contaFuncionarioPorTurma(Turma turma) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "SELECT COUNT(*) AS counter, t.funcionario FROM turma t LEFT JOIN turma_participante tp on " +
                "t.codigo = tp.turma WHERE t.codigo = ? GROUP BY t.funcionario ORDER BY counter ASC";
        return runner.execute(query, turma.getCodigo());
    }

    public int adicionaParticipanteATurma(int codigoTurma, Funcionario funcionarioSalvo) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "INSERT INTO turma_participante (turma, funcionario) VALUES (?,?)";
        return runner.execute(query, codigoTurma, funcionarioSalvo.getCodigo());
    }

    public void removeParticipanteDaTurma(int turmaCodigo, Funcionario funcionario) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "DELETE FROM turma_participante tp WHERE tp.turma = ? and tp.funcionario = ?";
        runner.execute(query, turmaCodigo, funcionario.getCodigo());
    }

    public List<TurmaParticipante> recuperaTodosParticipantes() throws SQLException {
        BeanListHandler<TurmaParticipante> beanListHandler = new BeanListHandler<>(TurmaParticipante.class);
        QueryRunner runner = new QueryRunner(DataSourceConfig.getInstance());
        String query = "SELECT * FROM turma_participante";
        return runner.query(query, beanListHandler);
    }
}
