package classes.JSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import classes.DTO.UnidadeSaude;
import interfaces.IUnidadeSaude;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class UnidadeSaudeJSON implements IUnidadeSaude {

	private List<UnidadeSaude> lista = new ArrayList<UnidadeSaude>();
	
	final String CAMINHO = "dadosJSON/unidadeSaude.json";
	
	public List<UnidadeSaude> getLista(){
		return lista;
	}
	
	public void setLista(List<UnidadeSaude> lista) {
		this.lista = lista;
	}
	
	public void adicionar(UnidadeSaude us) {
		us.setId((lista.size() + 1));
		lista.add(us);
	}
	
	public boolean inserir(UnidadeSaude us) {
		setLista(procurarTodas());
		adicionar(us);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		FileWriter file;
		try {
			file = new FileWriter(CAMINHO);
			file.write(gson.toJson(lista));
			file.close();
			return true;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<UnidadeSaude> procurarTodas() {
	    BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<UnidadeSaude>>(){}.getType();
	    lista = new ArrayList<UnidadeSaude>();
	    lista = new Gson().fromJson(bufferedReader, listType);
	    return lista;
	}
	
}