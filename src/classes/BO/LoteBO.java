package classes.BO;

import classes.DTO.Lote;
import classes.DAO.LoteDAO;
import java.util.List;

public class LoteBO {

	public boolean inserir(Lote lote){
        if (existe(lote) != true) {
            LoteDAO loteDAO = new LoteDAO();
            return loteDAO.inserir(lote);
        }
        return false;
    }
	
	public Lote procurarId(Lote lote) {
		LoteDAO loteDAO = new LoteDAO();
		return loteDAO.procurarId(lote);
	}
	
	public List<Lote> procurarTodosPorIdUS(Lote lote){
        LoteDAO loteDAO = new LoteDAO();
        return loteDAO.procurarTodosPorIdUS(lote);
    }
	
	public boolean existe(Lote lote){
        LoteDAO loteDAO = new LoteDAO();
        return loteDAO.existe(lote);
    }
	
}