package classes.DAO;

import classes.DTO.Funcionario;
import classes.DTO.UnidadeSaude;
import classes.BO.UnidadeSaudeBO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Conexao;

public class FuncionarioDAO {

	final String NOMEDATABELA = "FUNCIONARIO";
	
	public Funcionario procurarId(Funcionario func) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE NUMEROCNS_FUN = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, func.getNumeroCNS());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setNumeroCNS(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setDataNascimento(rs.getDate(3).toLocalDate());
				obj.setCpf(rs.getString(4));
				obj.setSenha(rs.getString(5));
				
				UnidadeSaude us = new UnidadeSaude(rs.getInt(6));
				UnidadeSaudeBO usBO = new UnidadeSaudeBO();
				us = usBO.procurarId(us);
				
				obj.setUnidadeSaude(us);
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
	
	public Funcionario procurarIdSenha(Funcionario func) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE NUMEROCNS_FUN = ? AND SENHA = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, func.getNumeroCNS());
			ps.setString(2, func.getSenha());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Funcionario obj = new Funcionario();
				obj.setNumeroCNS(rs.getInt(1));
				obj.setNome(rs.getString(2));
				obj.setDataNascimento(rs.getDate(3).toLocalDate());
				obj.setCpf(rs.getString(4));
				obj.setSenha(rs.getString(5));
				
				UnidadeSaude us = new UnidadeSaude(rs.getInt(6));
				UnidadeSaudeBO usBO = new UnidadeSaudeBO();
				us = usBO.procurarId(us);
				
				obj.setUnidadeSaude(us);
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
	
	public List<Funcionario> procurarTodosPorIdUS(Funcionario func) {
        try {
        	Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE ID_US = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, func.getUnidadeSaude().getId());
            ResultSet rs = ps.executeQuery();
            List<Funcionario> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<Funcionario> montarLista(ResultSet rs) {
        List<Funcionario> listObj = new ArrayList<Funcionario>();
        try {
            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setNumeroCNS(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setDataNascimento(rs.getDate(3).toLocalDate());
                obj.setCpf(rs.getString(4));
                obj.setSenha(rs.getString(5));
                
                UnidadeSaude us = new UnidadeSaude(rs.getInt(6));
				UnidadeSaudeBO usBO = new UnidadeSaudeBO();
				us = usBO.procurarId(us);
                
                obj.setUnidadeSaude(us);
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}