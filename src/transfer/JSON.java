package transfer;

import classes.JSON.*;

public class JSON extends Transferidor {

	public JSON() {
		administrador = new AdministradorJSON();
		cidadao = new CidadaoJSON();
		funcionario = new FuncionarioJSON();
		lote = new LoteJSON();
		registroVacina = new RegistroVacinaJSON();
		unidadeSaude = new UnidadeSaudeJSON();
	}
	
}