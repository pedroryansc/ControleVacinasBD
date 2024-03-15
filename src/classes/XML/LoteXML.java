package classes.XML;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import classes.DTO.Lote;
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
		setLista(ler());
		adicionar(lote);
		
		Element config = new Element("lotes");
		Document documento = new Document(config);
		
		Element titulo = new Element("titulo");
		titulo.setText("Lotes");
		
		config.addContent(titulo);
		
		for(int i = 0; i < lista.size(); i++) {
			Element lote = new Element("lote");
			
			lote.setAttribute("codigo", String.valueOf(lista.get(i).getCodigo()));
			
			Element nomeVacina = new Element("nomeVacina");
			nomeVacina.setText(lista.get(i).getNomeVacina());
			
			Element laboratorio = new Element("laboratorio");
			laboratorio.setText(lista.get(i).getLaboratorio());
			
			Element unidadeSaude = new Element("unidadeSaude");
			unidadeSaude.setText(lista.get(i).getUnidadeSaude());
			
			lote.addContent(nomeVacina);
			lote.addContent(laboratorio);
			lote.addContent(unidadeSaude);
			config.addContent(lote);
	}

	public List<Lote> ler(){
		return lista;
	}
		
	public List<Lote> procurarTodosPorIdUS(Lote Lote) {
		return null;
	}

}