package classes.BO;

import classes.DAO.RegistroVacinaDAO;
import classes.DTO.RegistroVacina;

public class RegistroVacinaBO {

	public boolean inserir(RegistroVacina registro){
        RegistroVacinaDAO registroDAO = new RegistroVacinaDAO();
        return registroDAO.inserir(registro);
    }
	
}