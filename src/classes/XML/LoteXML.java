package classes.XML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import classes.DTO.Lote;
import classes.DTO.UnidadeSaude;
import interfaces.ILote;

public class LoteXML implements ILote {

	private List<Lote> lista = new ArrayList<Lote>();
	
	final String CAMINHO = "dadosXML/lote.xml";
	
	public List<Lote> getLista() {
		return lista;
	}

	public void setLista(List<Lote> lista) {
		this.lista = lista;
	}
	
	public void adicionar(Lote lote) {
		lista.add(lote);
	}

	public boolean inserir(Lote lote) {
		File file = new File(CAMINHO);
		if(file.exists())
			setLista(ler());
		adicionar(lote);
		
		Element config = new Element("lotes");
		Document documento = new Document(config);
		
		Element titulo = new Element("titulo");
		titulo.setText("Lotes");
		
		config.addContent(titulo);
		
		for(int i = 0; i < lista.size(); i++) {
			Element lo = new Element("lote");
			
			lo.setAttribute("codigo", lista.get(i).getCodigo());
			
			Element nomeVacina = new Element("nomeVacina");
			nomeVacina.setText(lista.get(i).getNomeVacina());
			
			Element laboratorio = new Element("laboratorio");
			laboratorio.setText(lista.get(i).getLaboratorio());
			
			Element unidadeSaude = new Element("unidadeSaude");
			unidadeSaude.setText(String.valueOf(lista.get(i).getUnidadeSaude().getId()));
			
			lo.addContent(nomeVacina);
			lo.addContent(laboratorio);
			lo.addContent(unidadeSaude);
			config.addContent(lo);
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

	public List<Lote> ler(){
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaLote = config.getChildren("lote");
		
		lista = new ArrayList<Lote>();
		
		for(Iterator iter = listaLote.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			Lote lote = new Lote();
			lote.setCodigo(element.getAttributeValue("codigo"));
			lote.setNomeVacina(element.getChildText("nomeVacina"));
			lote.setLaboratorio(element.getChildText("laboratorio"));
			
			UnidadeSaude us = new UnidadeSaude(Integer.parseInt(element.getChildText("unidadeSaude")));
			UnidadeSaudeXML usXML = new UnidadeSaudeXML();
			us = usXML.procurarId(us);
			
			lote.setUnidadeSaude(us);
			
			lista.add(lote);
		}
		
		return lista;
	}
		
	public List<Lote> procurarTodosPorIdUS(Lote lote) {
		lista = new ArrayList<Lote>();
		
		File file = new File(CAMINHO);
		if(file.exists()) {
			
			Document doc = null;
			SAXBuilder builder = new SAXBuilder();
			try {
				doc = builder.build(CAMINHO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Element config = doc.getRootElement();
			List listaLote = config.getChildren("lote");
			
			for(Iterator iter = listaLote.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				
				if(Integer.parseInt(element.getChildText("unidadeSaude")) == lote.getUnidadeSaude().getId()) {
					
					Lote lo = new Lote();
					lo.setCodigo(element.getAttributeValue("codigo"));
					lo.setNomeVacina(element.getChildText("nomeVacina"));
					lo.setLaboratorio(element.getChildText("laboratorio"));
					
					UnidadeSaude us = new UnidadeSaude(Integer.parseInt(element.getChildText("unidadeSaude")));
					UnidadeSaudeXML usXML = new UnidadeSaudeXML();
					us = usXML.procurarId(us);
					
					lo.setUnidadeSaude(us);
					
					lista.add(lo);
				}
			}
		}
		
		return lista;
	}
	
	public Lote procurarCodigo(Lote lote) {
		setLista(ler());
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getCodigo().equals(lote.getCodigo()))
				return lista.get(i);
		}
		return null;
	}

}