package classes.XML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import classes.DTO.Funcionario;
import classes.DTO.UnidadeSaude;
import interfaces.IFuncionario;

public class FuncionarioXML implements IFuncionario {

	List<Funcionario> lista = new ArrayList<Funcionario>();
	
	final String CAMINHO = "dadosXML/funcionario.xml";
	
	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public Funcionario procurarId(Funcionario func) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaFunc = config.getChildren("funcionario");
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		lista = new ArrayList<Funcionario>();
		
		for(Iterator iter = listaFunc.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			Funcionario fu = new Funcionario();
			fu.setNumeroCNS(Integer.parseInt(element.getAttributeValue("numeroCNS")));
			fu.setNome(element.getChildText("nome"));
			fu.setDataNascimento(LocalDate.parse(element.getChildText("dataNascimento"), dataFormato));
			fu.setCpf(element.getChildText("cpf"));
			fu.setSenha(element.getChildText("senha"));
			
			UnidadeSaude us = new UnidadeSaude(Integer.parseInt(element.getChildText("unidadeSaude")));
			UnidadeSaudeXML usXML = new UnidadeSaudeXML();
			us = usXML.procurarId(us);
			
			fu.setUnidadeSaude(us);
			
			lista.add(fu);
		}
		
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getNumeroCNS() == func.getNumeroCNS())
				return lista.get(i);
		}
		
		return null;
	}
	
	public Funcionario procurarIdSenha(Funcionario func) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaFunc = config.getChildren("funcionario");
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		lista = new ArrayList<Funcionario>();
		
		for(Iterator iter = listaFunc.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			Funcionario fu = new Funcionario();
			fu.setNumeroCNS(Integer.parseInt(element.getAttributeValue("numeroCNS")));
			fu.setNome(element.getChildText("nome"));
			fu.setDataNascimento(LocalDate.parse(element.getChildText("dataNascimento"), dataFormato));
			fu.setCpf(element.getChildText("cpf"));
			fu.setSenha(element.getChildText("senha"));
			
			UnidadeSaude us = new UnidadeSaude(Integer.parseInt(element.getChildText("unidadeSaude")));
			UnidadeSaudeXML usXML = new UnidadeSaudeXML();
			us = usXML.procurarId(us);
			
			fu.setUnidadeSaude(us);
			
			lista.add(fu);
		}
		
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getNumeroCNS() == func.getNumeroCNS() && lista.get(i).getSenha().equals(func.getSenha()))
				return lista.get(i);
		}
		
		return null;
	}

	public List<Funcionario> procurarTodosPorIdUS(Funcionario func) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaFunc = config.getChildren("funcionario");
		
		lista = new ArrayList<Funcionario>();
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(Iterator iter = listaFunc.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			if(Integer.parseInt(element.getChildText("unidadeSaude")) == func.getUnidadeSaude().getId()) {
			
				Funcionario fu = new Funcionario();
				fu.setNumeroCNS(Integer.parseInt(element.getAttributeValue("numeroCNS")));
				fu.setNome(element.getChildText("nome"));
				fu.setDataNascimento(LocalDate.parse(element.getChildText("dataNascimento"), dataFormato));
				fu.setCpf(element.getChildText("cpf"));
				fu.setSenha(element.getChildText("senha"));
				
				UnidadeSaude us = new UnidadeSaude(Integer.parseInt(element.getChildText("unidadeSaude")));
				UnidadeSaudeXML usXML = new UnidadeSaudeXML();
				us = usXML.procurarId(us);
				
				fu.setUnidadeSaude(us);
				
				lista.add(fu);
			}
		}
		
		return lista;
	}

}