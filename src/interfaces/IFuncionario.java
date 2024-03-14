package interfaces;

import java.util.List;

import classes.DTO.Funcionario;

public interface IFuncionario {

	public Funcionario procurarIdSenha(Funcionario func);
	public List<Funcionario> procurarTodosPorIdUS(Funcionario func);
	
}