package interfaces;

import java.util.List;

import classes.DTO.Lote;

public interface ILote {

	public boolean inserir(Lote lote);
	public List<Lote> procurarTodosPorIdUS(Lote Lote);
	
}