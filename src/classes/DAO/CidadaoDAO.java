package classes.DAO;

import classes.DTO.Cidadao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conf.Conexao;

public class CidadaoDAO {

	final String NOMEDATABELA = "CIDADAO";
	
	public Cidadao procurarId(Cidadao cidadao) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE NUMEROCNS_CID = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cidadao.getNumeroCNS());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Cidadao obj = new Cidadao();
				obj.setNumeroCNS(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setDataNascimento(rs.getDate(3).toLocalDate());
				obj.setCpf(rs.getString(4));
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