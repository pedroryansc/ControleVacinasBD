package classes.DTO;
public class UnidadeSaude extends Geral {

	private String nome;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String telefone;
	
	public UnidadeSaude(int id, String nome, String rua, String bairro, String cidade, String estado, String telefone) {
		super(id);
		setNome(nome);
		setRua(rua);
		setBairro(bairro);
		setCidade(cidade);
		setEstado(estado);
		setTelefone(telefone);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnidadeSaude [nome=");
		builder.append(nome);
		builder.append(", rua=");
		builder.append(rua);
		builder.append(", bairro=");
		builder.append(bairro);
		builder.append(", cidade=");
		builder.append(cidade);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append("]");
		return builder.toString();
	}
}