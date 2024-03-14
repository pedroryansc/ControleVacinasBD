package interfaces;

import java.util.List;

import classes.DTO.RegistroVacina;

public interface IRegistroVacina {

	public boolean inserir(RegistroVacina registro);
	public List<RegistroVacina> procurarTodosPorId(RegistroVacina registro);
	
}