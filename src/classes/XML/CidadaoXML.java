package classes.XML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import classes.DTO.Cidadao;
import interfaces.ICidadao;

public class CidadaoXML implements ICidadao {

	private List<Cidadao> lista = new ArrayList<Cidadao>();
	
	final String CAMINHO = "dadosXML/cidadao.xml";
	
	public List<Cidadao> getLista() {
		return lista;
	}

	public void setLista(List<Cidadao> lista) {
		this.lista = lista;
	}

	public Cidadao procurarId(Cidadao cidadao) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaCid = config.getChildren("cidadao");
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		lista = new ArrayList<Cidadao>();
		
		for(Iterator iter = listaCid.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			Cidadao cid = new Cidadao();
			cid.setNumeroCNS(Integer.parseInt(element.getAttributeValue("numeroCNS")));
			cid.setNome(element.getChildText("nome"));
			cid.setDataNascimento(LocalDate.parse(element.getChildText("dataNascimento"), dataFormato));
			cid.setCpf(element.getChildText("cpf"));
			
			lista.add(cid);
		}
		
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getNumeroCNS() == cidadao.getNumeroCNS())
				return lista.get(i);
		}
		
		return null;
	}
}