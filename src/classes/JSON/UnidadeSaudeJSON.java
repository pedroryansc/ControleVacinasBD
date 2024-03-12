package classes.JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import classes.DTO.UnidadeSaude;

public class UnidadeSaudeJSON {

	private List<UnidadeSaude> lista = new ArrayList<UnidadeSaude>();
	
	public List<UnidadeSaude> getLista(){
		return lista;
	}
	
	public void setLista(List<UnidadeSaude> lista) {
		this.lista = lista;
	}
	
	public void adicionar(UnidadeSaude us) {
		lista.add(us);
	}
	
	public void gravar() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		FileWriter writer;
		try {
			writer = new FileWriter("dadosJSON/unidadeSaude.json");
			writer.write(gson.toJson(lista));
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}