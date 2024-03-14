package classes.DAO;

import classes.BO.CidadaoBO;
import classes.BO.FuncionarioBO;
import classes.BO.LoteBO;
import classes.BO.UnidadeSaudeBO;
import classes.DTO.Cidadao;
import classes.DTO.Funcionario;
import classes.DTO.Lote;
import classes.DTO.RegistroVacina;
import classes.DTO.UnidadeSaude;

import interfaces.IRegistroVacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Conexao;

public class RegistroVacinaDAO implements IRegistroVacina {

	final String NOMEDATABELA = "REGISTROVACINA";
	
	public boolean inserir(RegistroVacina registro) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (DATA, LOTE_CODIGO, NUMEROCNS_CID, NUMEROCNS_FUN, DOSE, ID_US) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(registro.getData()));
            ps.setString(2, registro.getLote().getCodigo());
            ps.setInt(3, registro.getCidadao().getNumeroCNS());
            ps.setInt(4, registro.getVacinador().getNumeroCNS());
            ps.setInt(5, registro.getDose());
            ps.setInt(6, registro.getUnidadeSaude().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public List<RegistroVacina> procurarTodosPorId(RegistroVacina registro) {
        try {
        	Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE NUMEROCNS_CID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, registro.getCidadao().getNumeroCNS());
            ResultSet rs = ps.executeQuery();
            List<RegistroVacina> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public List<RegistroVacina> montarLista(ResultSet rs) {
        List<RegistroVacina> listObj = new ArrayList<RegistroVacina>();
        try {
            while (rs.next()) {
                RegistroVacina obj = new RegistroVacina();
                obj.setId(rs.getInt(1));
                obj.setData(rs.getDate(2).toLocalDate());
                
                Lote lote = new Lote(rs.getString(3));
                LoteBO loteBO = new LoteBO();
                lote = loteBO.procurarId(lote);
                
                obj.setLote(lote);
                
                Cidadao cidadao = new Cidadao(rs.getInt(4));
                CidadaoBO cidadaoBO = new CidadaoBO();
                cidadao = cidadaoBO.procurarId(cidadao);
                
                obj.setCidadao(cidadao);
                
                Funcionario func = new Funcionario(rs.getInt(5));
                FuncionarioBO funcBO = new FuncionarioBO();
                func = funcBO.procurarId(func);
                
                obj.setVacinador(func);
                obj.setDose(rs.getInt(6));
                
                UnidadeSaude us = new UnidadeSaude(rs.getInt(7));
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