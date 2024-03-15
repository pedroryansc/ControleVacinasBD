package classes.XML;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import classes.DTO.UnidadeSaude;
import interfaces.IUnidadeSaude;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class UnidadeSaudeXML implements IUnidadeSaude {

	private List<UnidadeSaude> lista = new ArrayList<UnidadeSaude>();
	
	final String CAMINHO = "dadosXML/unidadeSaude.xml";
	
	public List<UnidadeSaude> getLista() {
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
		
		Element config = new Element("unidadesSaude");
		Document documento = new Document(config);
		
		Element titulo = new Element("titulo");
		titulo.setText("Unidades de Saúde");
		
		config.addContent(titulo);
		
		for(int i = 0; i < lista.size(); i++) {
			Element unidadeSaude = new Element("unidadeSaude");
			
			unidadeSaude.setAttribute("id", String.valueOf(lista.get(i).getId()));
			
			Element nome = new Element("nome");
			nome.setText(lista.get(i).getNome());
			
			Element rua = new Element("rua");
			rua.setText(lista.get(i).getRua());
			
			Element bairro = new Element("bairro");
			bairro.setText(lista.get(i).getBairro());
			
			Element cidade = new Element("cidade");
			cidade.setText(lista.get(i).getCidade());
			
			Element estado = new Element("estado");
			estado.setText(lista.get(i).getEstado());
			
			Element telefone = new Element("telefone");
			telefone.setText(lista.get(i).getTelefone());
			
			unidadeSaude.addContent(nome);
			unidadeSaude.addContent(rua);
			unidadeSaude.addContent(bairro);
			unidadeSaude.addContent(cidade);
			unidadeSaude.addContent(estado);
			unidadeSaude.addContent(telefone);
			config.addContent(unidadeSaude);
		}
		
		XMLOutputter xout = new XMLOutputter();
		try {
			BufferedWriter arquivo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CAMINHO), "UTF-8"));
			xout.output(documento, arquivo);
			return true;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<UnidadeSaude> procurarTodas() {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaUS = config.getChildren("unidadeSaude");
		
		lista = new ArrayList<UnidadeSaude>();
		
		for(Iterator iter = listaUS.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			UnidadeSaude us = new UnidadeSaude();
			us.setId(Integer.parseInt(element.getAttributeValue("id")));
			us.setNome(element.getChildText("nome"));
			us.setRua(element.getChildText("rua"));
			us.setBairro(element.getChildText("bairro"));
			us.setCidade(element.getChildText("cidade"));
			us.setEstado(element.getChildText("estado"));
			us.setTelefone(element.getChildText("telefone"));
			
			lista.add(us);
		}
		
		return lista;
	}
	
	public UnidadeSaude procurarId(UnidadeSaude us) {
		setLista(procurarTodas());
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getId() == us.getId())
				return lista.get(i);
		}
		return null;
	}
}