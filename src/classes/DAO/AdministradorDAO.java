package classes.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conf.Conexao;

import classes.DTO.Administrador;

public class AdministradorDAO {

	final String NOMEDATABELA = "ADMINISTRADOR";
	
	public Administrador procurarIdSenha(Administrador func) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE NUMEROCNS_ADM = ? AND SENHA = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, func.getNumeroCNS());
			ps.setString(2, func.getSenha());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Administrador obj = new Administrador();
				obj.setNumeroCNS(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setDataNascimento(rs.getDate(3).toLocalDate());
				obj.setCpf(rs.getString(4));
				obj.setSenha(rs.getString(5));
				ps.close();
				rs.close();
				conn.close();
				return obj;
			} else {
				ps.close();
				rs.close();
				conn.close();
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}