package classes.DTO;
import java.time.LocalDate;

public class RegistroVacina extends Geral {
	
	private LocalDate data;
	private Lote lote;
	private Cidadao cidadao;
	private Funcionario vacinador;
	private int dose = 0;
	private UnidadeSaude unidadeSaude;
	
	public RegistroVacina() {
		
	}
	
	public RegistroVacina(Cidadao cidadao) {
		setCidadao(cidadao);
	}
	
	public RegistroVacina(int id, LocalDate data, Lote lote, Cidadao cidadao, Funcionario vacinador, int dose, UnidadeSaude unidadeSaude) {
		super(id);
		setData(data);
		setLote(lote);
		setCidadao(cidadao);
		setVacinador(vacinador);
		setDose(dose);
		setUnidadeSaude(unidadeSaude);
	}
	
	// Ou, se for uma vacina de dose �nica, o construtor abaixo possibilita cadastrar um registro de vacina sem informar a dose:
	
	public RegistroVacina(int id, LocalDate data, Lote lote, Cidadao cidadao, Funcionario vacinador, UnidadeSaude unidadeSaude) {
		super(id);
		setData(data);
		setLote(lote);
		setCidadao(cidadao);
		setVacinador(vacinador);
		setUnidadeSaude(unidadeSaude);
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public Cidadao getCidadao() {
		return cidadao;
	}
	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}
	public Funcionario getVacinador() {
		return vacinador;
	}
	public void setVacinador(Funcionario vacinador) {
		this.vacinador = vacinador;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}
	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}
	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistroVacina [data=");
		builder.append(data);
		builder.append(", lote=");
		builder.append(lote);
		builder.append(", cidadao=");
		builder.append(cidadao);
		builder.append(", vacinador=");
		builder.append(vacinador);
		builder.append(", dose=");
		builder.append(dose);
		builder.append(", unidadeSaude=");
		builder.append(unidadeSaude);
		builder.append("]");
		return builder.toString();
	}
}