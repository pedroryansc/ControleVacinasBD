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

import classes.DTO.Funcionario;
import interfaces.IFuncionario;

public class FuncionarioJSON implements IFuncionario {

private List<Funcionario> lista = new ArrayList<Funcionario>();
	
	final String CAMINHO = "dadosJSON/funcionario.json";

	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}
	
	public Funcionario procurarIdSenha(Funcionario func) {
	    BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<Funcionario>>(){}.getType();
	    lista = new ArrayList<Funcionario>();
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
	    lista = gson.fromJson(bufferedReader, listType);
	    
	    for(int i = 0; i < lista.size(); i++) {
	    	if(lista.get(i).getNumeroCNS() == func.getNumeroCNS() && lista.get(i).getSenha().equals(func.getSenha()))
	    		return lista.get(i);
	    }
	    
	    return null;
	}
	
	public List<Funcionario> procurarTodosPorIdUS(Funcionario func){
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(CAMINHO));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Type listType = new TypeToken<ArrayList<Funcionario>>(){}.getType();
	    lista = new ArrayList<Funcionario>();
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
	    lista = gson.fromJson(bufferedReader, listType);
	    
	    List<Funcionario> funcsUS = new ArrayList<Funcionario>();
	    
	    if(lista != null) {
		    for(int i = 0; i < lista.size(); i++) {
		    	if(lista.get(i).getUnidadeSaude().getId() == func.getUnidadeSaude().getId())
		    		funcsUS.add(lista.get(i));
		    }
	    }
	    
	    return funcsUS;
	}
	
}