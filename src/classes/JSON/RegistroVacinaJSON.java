package classes.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import classes.DTO.RegistroVacina;

public class RegistroVacinaJSON {

	private List<RegistroVacina> lista = new ArrayList<RegistroVacina>();
	
	final String CAMINHO = "dadosJSON/registrovacina.json";

	public List<RegistroVacina> getLista() {
		return lista;
	}

	public void setLista(List<RegistroVacina> lista) {
		this.lista = lista;
	}
	
	public void adicionar(RegistroVacina registro) {
		registro.setId(lista.size() + 1);
		lista.add(registro);
	}
	
	public boolean gravar() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
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
	
	public List<RegistroVacina> ler() {
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
	    Type listType = new TypeToken<ArrayList<RegistroVacina>>(){}.getType();
	    lista = new ArrayList<RegistroVacina>();
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
	    lista = gson.fromJson(bufferedReader, listType);
	    return lista;
	}

	public List<RegistroVacina> procurarTodosPorId(RegistroVacina registro) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<RegistroVacina>>(){}.getType();
	    lista = new ArrayList<RegistroVacina>();
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
	    lista = gson.fromJson(bufferedReader, listType);
	    
	    List<RegistroVacina> registrosCid = new ArrayList<RegistroVacina>();
	    
	    if(lista != null) {
		    for(int i = 0; i < lista.size(); i++) {
		    	if(lista.get(i).getCidadao().getNumeroCNS() == registro.getCidadao().getNumeroCNS())
		    		registrosCid.add(lista.get(i));
		    }
	    }
	    
	    return registrosCid;
	}
	
}