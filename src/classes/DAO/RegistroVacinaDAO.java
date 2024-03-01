package classes.DAO;

import classes.DTO.RegistroVacina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conf.Conexao;

public class RegistroVacinaDAO {

	final String NOMEDATABELA = "REGISTROVACINA";
	
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