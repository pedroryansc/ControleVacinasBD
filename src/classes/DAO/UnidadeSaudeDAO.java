package classes.DAO;

import classes.DTO.UnidadeSaude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Conexao;

public class UnidadeSaudeDAO {

final String NOMEDATABELA = "UNIDADESAUDE";
	
	public boolean inserir(UnidadeSaude us) {
	    try {
	        Connection conn = Conexao.conectar();
	        String sql = "INSERT INTO " + NOMEDATABELA + " (ID_US, NOME, RUA, BAIRRO, CIDADE, ESTADO, TELEFONE) VALUES (?, ?, ?, ?, ?, ?, ?);";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, us.getId());
	        ps.setString(2, us.getNome());
	        ps.setString(3, us.getRua());
	        ps.setString(4, us.getBairro());
	        ps.setString(5, us.getCidade());
	        ps.setString(6, us.getEstado());
	        ps.setString(7, us.getTelefone());
	        ps.executeUpdate();
	        ps.close();
	        conn.close();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

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
	
	public List<UnidadeSaude> procurarTodas() {
        try {
        	Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<UnidadeSaude> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<UnidadeSaude> montarLista(ResultSet rs) {
        List<UnidadeSaude> listObj = new ArrayList<UnidadeSaude>();
        try {
            while (rs.next()) {
                UnidadeSaude obj = new UnidadeSaude();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setRua(rs.getString(3));
                obj.setBairro(rs.getString(4));
                obj.setCidade(rs.getString(5));
                obj.setEstado(rs.getString(6));
                obj.setTelefone(rs.getString(7));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}