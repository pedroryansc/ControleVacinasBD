package transfer;

import classes.DAO.*;

public class SQL extends Transferidor {

	public SQL() {
		administrador = new AdministradorDAO();
		cidadao = new CidadaoDAO();
		funcionario = new FuncionarioDAO();
		lote = new LoteDAO();
		registroVacina = new RegistroVacinaDAO();
		unidadeSaude = new UnidadeSaudeDAO();
	}
	
}