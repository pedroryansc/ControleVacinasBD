package classes.DAO;

import classes.BO.UnidadeSaudeBO;
import classes.DTO.Lote;
import classes.DTO.UnidadeSaude;
import conf.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO {

	final String NOMEDATABELA = "LOTE";
	
	public boolean inserir(Lote lote) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (LOTE_CODIGO, NOMEVACINA, LABORATORIO, ID_US) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lote.getCodigo());
            ps.setString(2, lote.getNomeVacina());
            ps.setString(3, lote.getLaboratorio());
            ps.setInt(4, lote.getUnidadeSaude().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public boolean existe(Lote lote) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE LOTE_CODIGO = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lote.getCodigo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
        return false;
    }
	
	public List<Lote> procurarTodosPorIdUS(Lote lote) {
        try {
        	Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE ID_US = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, lote.getUnidadeSaude().getId());
            ResultSet rs = ps.executeQuery();
            List<Lote> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<Lote> montarLista(ResultSet rs) {
        List<Lote> listObj = new ArrayList<Lote>();
        try {
            while (rs.next()) {
                Lote obj = new Lote();
                obj.setCodigo(rs.getString(1));
                obj.setNomeVacina(rs.getString(2));
                obj.setLaboratorio(rs.getString(3));
                
                UnidadeSaude us = new UnidadeSaude(rs.getInt(4));
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
