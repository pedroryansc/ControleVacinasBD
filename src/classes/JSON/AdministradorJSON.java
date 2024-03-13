package classes.JSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import classes.DTO.Administrador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AdministradorJSON {

	private List<Administrador> lista = new ArrayList<Administrador>();
	
	final String CAMINHO = "dadosJSON/administrador.json";

	public List<Administrador> getLista() {
		return lista;
	}

	public void setLista(List<Administrador> lista) {
		this.lista = lista;
	}
	
	public Administrador procurarIdSenha(Administrador admin) {
	    BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<Administrador>>(){}.getType();
	    lista = new ArrayList<Administrador>();
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
	    lista = gson.fromJson(bufferedReader, listType);
	    
	    for(int i = 0; i < lista.size(); i++) {
	    	if(lista.get(i).getNumeroCNS() == admin.getNumeroCNS() && lista.get(i).getSenha().equals(admin.getSenha()))
	    		return lista.get(i);
	    }
	    
	    return null;
	}
	
}