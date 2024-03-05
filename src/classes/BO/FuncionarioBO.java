package classes.BO;

import java.util.List;

import classes.DAO.FuncionarioDAO;
import classes.DTO.Funcionario;

public class FuncionarioBO {

	public Funcionario procurarId(Funcionario func) {
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		return funcDAO.procurarId(func);
	}
	
	public List<Funcionario> procurarTodosPorIdUS(Funcionario func){
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        return funcDAO.procurarTodosPorIdUS(func);
    }
	
}
