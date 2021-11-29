/* Caio França Ricciardi, Murilo Gabriel Ferreira, Jônatas dos Santos de Sales e 
Flávio Henrique do Nascimento*/

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Aluno;
import jdbc.ConnectionFactory;
// import jdbc.*;

public class AlunoDAO {

	public void create(Aluno aluno) throws ClassNotFoundException {
		// SQL - INSERT;
		Connection banco = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "insert into aluno(matricula, nome, cidade) values (?, ?, ?)";
			stmt = banco.prepareStatement(sql);

			// Popular a query com parametros
			stmt.setString(1, aluno.getMatricula());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getCidade());

			stmt.executeUpdate();
			System.out.println("Aluno incluido com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("Erro ao tentar incluir aluno.");
		} finally {
			ConnectionFactory.closeConnection(banco, stmt);
		}
	}

	public List<Aluno> read() throws ClassNotFoundException {
		List<Aluno> listaAlunos = new ArrayList<Aluno>();

		Connection banco = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select matricula, nome, cidade from aluno";

		try {
			stmt = banco.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno();

				aluno.setMatricula(rs.getString("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCidade(rs.getString("cidade"));

				listaAlunos.add(aluno);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar ler Aluno...");
		} finally {
			ConnectionFactory.closeConnection(banco, stmt);
		}
		return listaAlunos;
	}

	public void update(Aluno aluno) throws ClassNotFoundException {
		// criar conexão
		Connection banco = ConnectionFactory.getConnection();

		// criar Statement
		PreparedStatement stmt = null;

		try {
			// Conferindo os dados
			System.out.println("Tentativa: UPDATE aluno SET nome = " + aluno.getNome() + ", cidade = "
					+ aluno.getCidade() + " where matricula = " + aluno.getMatricula());

			// SQL - UPDATE
			String sql = "UPDATE aluno SET nome =?, cidade =? where matricula =?";

			// Criando a query com os parametros
			stmt = banco.prepareStatement(sql);

			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getCidade());
			stmt.setString(3, aluno.getMatricula());

			// Executa a query
			int updateCount = stmt.executeUpdate();

			if (updateCount > 0) {
				System.out.println("Aluno atualizado com sucesso!");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar atualizar aluno.");
		} finally {
			ConnectionFactory.closeConnection(banco, stmt);
		}
	}

	public void delete(String matri) throws ClassNotFoundException {
		// criar conexão
		Connection banco = ConnectionFactory.getConnection();

		// criar Statement
		PreparedStatement stmt = null;

		try {
			// Conferindo os dados
			System.out.println("Tentativa: DELETE FROM aluno WHERE matricula = " + matri);

			// SQL - DELETE
			String sql = "DELETE FROM aluno WHERE matricula = ?";

			// Criando a query com os parametros
			stmt = banco.prepareStatement(sql);

			stmt.setString(1, matri);

			// Executa a query
			int updateCount = stmt.executeUpdate();

			if (updateCount > 0) {
				System.out.println("Aluno deletado com sucesso!");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar deletar aluno.");
		} finally {
			ConnectionFactory.closeConnection(banco, stmt);
		}
	}
}