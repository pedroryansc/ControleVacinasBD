package classes.BO;

import java.util.List;

import classes.DAO.RegistroVacinaDAO;
import classes.DTO.RegistroVacina;

public class RegistroVacinaBO {

	public boolean inserir(RegistroVacina registro){
        RegistroVacinaDAO registroDAO = new RegistroVacinaDAO();
        return registroDAO.inserir(registro);
    }
	
	public List<RegistroVacina> procurarTodosPorId(RegistroVacina registro){
        RegistroVacinaDAO registroDAO = new RegistroVacinaDAO();
        return registroDAO.procurarTodosPorId(registro);
    }
	
}