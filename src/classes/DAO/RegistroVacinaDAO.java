package classes.DAO;

import classes.DTO.RegistroVacina;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Conexao;

public class RegistroVacinaDAO {

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
	
	public List<RegistroVacina> pesquisarTodosPorId() {
        try {
        	Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE NUMEROCNS_CID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
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
                obj.setLote(null);
                obj.setCidadao(null);
                obj.setVacinador(null);
                obj.setDose(rs.getInt(6));
                obj.setUnidadeSaude(null);
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}