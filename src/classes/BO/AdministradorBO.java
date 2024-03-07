package classes.BO;

import classes.DAO.AdministradorDAO;
import classes.DTO.Administrador;

public class AdministradorBO {
	
	public Administrador procurarIdSenha(Administrador admin) {
		AdministradorDAO adminDAO = new AdministradorDAO();
		return adminDAO.procurarIdSenha(admin);
	}
	
}