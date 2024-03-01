package classes.DTO;

public class Geral { // Será utilizado para a injeção de dependência em outra classe, pois todas as outras classes fazem parte de Geral

	private int id;

	public Geral() {
		
	}
	
	public Geral(int id) {
		setId(id);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
