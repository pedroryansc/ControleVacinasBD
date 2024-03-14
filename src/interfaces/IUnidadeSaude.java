package interfaces;

import java.util.List;

import classes.DTO.UnidadeSaude;

public interface IUnidadeSaude {

	public boolean inserir(UnidadeSaude us);
	public List<UnidadeSaude> procurarTodas();
	
}