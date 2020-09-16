package br.arcadia.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.arcadia.entidade.Usuario;
import br.arcadia.jdbc.CNXJDBC;

public class UsuarioDAO {

	private final String SQL_INSERE_USUARIO = "INSERT INTO USUARIOS(NOME, EMAIL) VALUES ( ?, ?);";
	private final String SQL_ALTERA_USUARIO = "UPDATE USUARIOS SET NOME=?, EMAIL=? WHERE ID=?;";
	private final String SQL_EXCLUI_USUARIO = "DELETE FROM USUARIOS WHERE ID=?";
	private final String SQL_SELECIONA_USUARIO = "SELECT * FROM USUARIOS";

	private PreparedStatement pst = null;

	public void inserirUsuario(Usuario umUsuario) {
		try (	Connection conn = new CNXJDBC().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERE_USUARIO);) {
			pst.setString(1, umUsuario.getNome());
			pst.setString(2, umUsuario.getEMail());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}

	public ArrayList<Usuario> listarTodosUsuarios() {
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

		Usuario umUsuario;
		try (	Connection conn = new CNXJDBC().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_USUARIO);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				umUsuario = new Usuario();
				umUsuario.setCodigo(rs.getInt("ID"));
				umUsuario.setNome(rs.getString("NOME"));
				umUsuario.setEMail(rs.getString("EMAIL"));
				listaDeUsuarios.add(umUsuario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return listaDeUsuarios;
	}

	public boolean alterarUsuario(Usuario umUsuario) {
		boolean ret = false;
		try (	Connection conn = new CNXJDBC().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_USUARIO);
				ResultSet rs = pst.executeQuery();) {
			pst.setString(1, umUsuario.getNome());
			pst.setString(2, umUsuario.getEMail());
			pst.setInt(3, umUsuario.getCodigo());
			ret = pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

	public boolean excluiUsuario(Usuario umUsuario) {
		boolean ret = false;
		try (	Connection conn = new CNXJDBC().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_USUARIO);
				ResultSet rs = pst.executeQuery();) {
			pst.setInt(1, umUsuario.getCodigo());
			ret = pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		return ret;
	}

}
