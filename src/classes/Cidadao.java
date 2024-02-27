package classes;
import java.time.LocalDate;

public class Cidadao extends Geral {

	private int numeroCNS;
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	
	public Cidadao(int id, int numeroCNS, String nome, LocalDate dataNascimento, String cpf) {
		super(id);
		setNumeroCNS(numeroCNS);
		setNome(nome);
		setDataNascimento(dataNascimento);
		setCpf(cpf);
	}
	
	public int getNumeroCNS() {
		return numeroCNS;
	}
	public void setNumeroCNS(int numeroCNS) {
		this.numeroCNS = numeroCNS;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cidadao [numeroCNS=");
		builder.append(numeroCNS);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", dataNascimento=");
		builder.append(dataNascimento);
		builder.append(", cpf=");
		builder.append(cpf);
		builder.append("]");
		return builder.toString();
	}
}