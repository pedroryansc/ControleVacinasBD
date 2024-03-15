package transfer;

import classes.XML.*;

public class XML extends Transferidor {

	public XML() {
		administrador = new AdministradorXML();
		cidadao = new CidadaoXML();
		funcionario = new FuncionarioXML();
		lote = new LoteXML();
		registroVacina = new RegistroVacinaXML();
		unidadeSaude = new UnidadeSaudeXML();
	}
	
}