package classes.JSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import classes.DTO.Cidadao;

public class CidadaoJSON {

	private List<Cidadao> lista = new ArrayList<Cidadao>();
	
	final String CAMINHO = "dadosJSON/cidadao.json";

	public List<Cidadao> getLista() {
		return lista;
	}

	public void setLista(List<Cidadao> lista) {
		this.lista = lista;
	}
	
	public Cidadao procurarId(Cidadao cidadao) {
	    BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<Cidadao>>(){}.getType();
	    lista = new ArrayList<Cidadao>();
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
	    lista = gson.fromJson(bufferedReader, listType);
	    
	    for(int i = 0; i < lista.size(); i++) {
	    	if(lista.get(i).getNumeroCNS() == cidadao.getNumeroCNS())
	    		return lista.get(i);
	    }
	    
	    return null;
	}
	
}