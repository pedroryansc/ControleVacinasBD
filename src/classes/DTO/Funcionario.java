package classes.DTO;
import java.time.LocalDate;

public class Funcionario extends Cidadao {

	private String senha;
	private UnidadeSaude unidadeSaude;
	
	public Funcionario() {
		
	}
	
	public Funcionario(int numeroCNS) {
		setNumeroCNS(numeroCNS);
	}
	
	public Funcionario(UnidadeSaude unidadeSaude) {
		setUnidadeSaude(unidadeSaude);
	}
	
	public Funcionario(int numeroCNS, String senha) {
		setNumeroCNS(numeroCNS);
		setSenha(senha);
	}
	
	public Funcionario(int numeroCNS, String nome, LocalDate dataNascimento, String cpf, String senha, UnidadeSaude unidadeSaude) {
		super(numeroCNS, nome, dataNascimento, cpf);
		setSenha(senha);
		setUnidadeSaude(unidadeSaude);
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
		builder.append("Funcionario [senha=");
		builder.append(senha);
		builder.append(", unidadeSaude=");
		builder.append(unidadeSaude);
		builder.append("]");
		return builder.toString();
	}
}