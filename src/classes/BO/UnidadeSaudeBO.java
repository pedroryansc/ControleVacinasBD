package classes.BO;

import classes.DAO.UnidadeSaudeDAO;
import classes.DTO.UnidadeSaude;

public class UnidadeSaudeBO {

	public UnidadeSaude procurarId(UnidadeSaude us) {
		UnidadeSaudeDAO usDAO = new UnidadeSaudeDAO();
		return usDAO.procurarId(us);
	}
	
}
