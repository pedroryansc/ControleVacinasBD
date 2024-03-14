package transfer;

import interfaces.*;

public abstract class Transferidor {

	protected IAdministrador administrador;
	protected ICidadao cidadao;
	protected IFuncionario funcionario;
	protected ILote lote;
	protected IRegistroVacina registroVacina;
	protected IUnidadeSaude unidadeSaude;
	public IAdministrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(IAdministrador administrador) {
		this.administrador = administrador;
	}
	public ICidadao getCidadao() {
		return cidadao;
	}
	public void setCidadao(ICidadao cidadao) {
		this.cidadao = cidadao;
	}
	public IFuncionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(IFuncionario funcionario) {
		this.funcionario = funcionario;
	}
	public ILote getLote() {
		return lote;
	}
	public void setLote(ILote lote) {
		this.lote = lote;
	}
	public IRegistroVacina getRegistroVacina() {
		return registroVacina;
	}
	public void setRegistroVacina(IRegistroVacina registroVacina) {
		this.registroVacina = registroVacina;
	}
	public IUnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}
	public void setUnidadeSaude(IUnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}
	
	
	
}