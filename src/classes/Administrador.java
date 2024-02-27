package classes;
import java.time.LocalDate;

public class Administrador extends Cidadao {

	private String senha;
	
	public Administrador(int id, int numeroCNS, String nome, LocalDate dataNascimento, String cpf, String senha) {
		super(id, numeroCNS, nome, dataNascimento, cpf);
		setSenha(senha);
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Administrador [senha=");
		builder.append(senha);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}