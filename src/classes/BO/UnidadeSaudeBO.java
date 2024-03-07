package classes.BO;

import java.util.List;
import classes.DAO.UnidadeSaudeDAO;
import classes.DTO.UnidadeSaude;

public class UnidadeSaudeBO {

	public boolean inserir(UnidadeSaude us) {
		UnidadeSaudeDAO usDAO = new UnidadeSaudeDAO();
		return usDAO.inserir(us);
	}
	
	public UnidadeSaude procurarId(UnidadeSaude us) {
		UnidadeSaudeDAO usDAO = new UnidadeSaudeDAO();
		return usDAO.procurarId(us);
	}
	
	public List<UnidadeSaude> procurarTodas(){
		UnidadeSaudeDAO usDAO = new UnidadeSaudeDAO();
		return usDAO.procurarTodas();
	}
	
}