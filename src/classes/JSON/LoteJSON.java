package classes.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import classes.DTO.Lote;

public class LoteJSON {

	private List<Lote> lista = new ArrayList<Lote>();
	
	final String CAMINHO = "dadosJSON/lote.json";

	public List<Lote> getLista() {
		return lista;
	}

	public void setLista(List<Lote> lista) {
		this.lista = lista;
	}
	
	public void adicionar(Lote lote) {
		lista.add(lote);
	}
	
	public boolean gravar() {
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
	
	public List<Lote> ler() {
	    BufferedReader bufferedReader = null;
	    File file = new File(CAMINHO);
	    if(!(file.exists())) {
	    	gravar();
	    }	
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<Lote>>(){}.getType();
	    lista = new ArrayList<Lote>();
	    lista = new Gson().fromJson(bufferedReader, listType);
	    return lista;
	}
	
	public List<Lote> procurarTodosPorIdUS(Lote lote){
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<Lote>>(){}.getType();
	    lista = new ArrayList<Lote>();
	    lista = new Gson().fromJson(bufferedReader, listType);
	    
	    List<Lote> lotesUS = new ArrayList<Lote>();
	    
	    if(lista != null) {
		    for(int i = 0; i < lista.size(); i++) {
		    	if(lista.get(i).getUnidadeSaude().getId() == lote.getUnidadeSaude().getId())
		    		lotesUS.add(lista.get(i));
		    }
	    }
	    
	    return lotesUS;
	}
	
}