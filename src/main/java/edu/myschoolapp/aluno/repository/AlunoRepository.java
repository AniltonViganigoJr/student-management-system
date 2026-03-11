package edu.myschoolapp.aluno.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.myschoolapp.aluno.config.ConnectionFactory;
import edu.myschoolapp.aluno.model.Aluno;

public class AlunoRepository {

    public void ativarAluno(Long id) {

        String sql = """
                    UPDATE aluno
                    SET ativo = true
                    WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Aluno não encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ativar aluno: ", e);
        }
    }

    public void desativarAluno(Long id) {

        String sql = """
                    UPDATE aluno
                    SET ativo = false
                    WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Aluno não encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao desativar aluno: ", e);
        }
    }

    public List<Aluno> listarAlunosByStatus(boolean ativo) {

        List<Aluno> alunos = new ArrayList<>();

        String sql = """
                    SELECT id, nome, cpf, data_nascimento, email, ativo, telefone
                    FROM aluno
                    WHERE ativo = ?
                    ORDER BY nome
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, ativo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getLong("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("email"),
                            rs.getBoolean("ativo"),
                            rs.getString("telefone"));
                    alunos.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos ativos.");
        }
        return alunos;
    }

    public void salvarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, cpf, data_nascimento, email, ativo, telefone) VALUES (?,?,?,?,?,?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(4, aluno.getEmail());
            stmt.setBoolean(5, aluno.isAtivo());
            stmt.setString(6, aluno.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }

}
