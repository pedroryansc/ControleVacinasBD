package classes.DAO;

import classes.DTO.UnidadeSaude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conf.Conexao;

public class UnidadeSaudeDAO {

final String NOMEDATABELA = "UNIDADESAUDE";
	
	public UnidadeSaude procurarId(UnidadeSaude us) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE ID_US = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, us.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				UnidadeSaude obj = new UnidadeSaude();
				obj.setId(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setRua(rs.getString(3));
				obj.setBairro(rs.getString(4));
				obj.setCidade(rs.getString(5));
				obj.setEstado(rs.getString(6));
				obj.setTelefone(rs.getString(7));
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