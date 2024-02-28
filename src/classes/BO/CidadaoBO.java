package classes.BO;

import classes.DTO.Cidadao;
import classes.DAO.CidadaoDAO;

public class CidadaoBO {

	public Cidadao procurarId(Cidadao cidadao) {
		CidadaoDAO cidadaoDAO = new CidadaoDAO();
		return cidadaoDAO.procurarId(cidadao);
	}
	
}