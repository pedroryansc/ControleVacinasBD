package classes.XML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import classes.DTO.Administrador;
import interfaces.IAdministrador;

public class AdministradorXML implements IAdministrador {

	List<Administrador> lista = new ArrayList<Administrador>();
	
	final String CAMINHO = "dadosXML/administrador.xml";
	
	public List<Administrador> getLista() {
		return lista;
	}

	public void setLista(List<Administrador> lista) {
		this.lista = lista;
	}

	public Administrador procurarIdSenha(Administrador admin) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaAdmin = config.getChildren("administrador");
		
		lista = new ArrayList<Administrador>();
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(Iterator iter = listaAdmin.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			Administrador ad = new Administrador();
			ad.setNumeroCNS(Integer.parseInt(element.getAttributeValue("numeroCNS")));
			ad.setNome(element.getChildText("nome"));
			ad.setDataNascimento(LocalDate.parse(element.getChildText("dataNascimento"), dataFormato));
			ad.setCpf(element.getChildText("cpf"));
			ad.setSenha(element.getChildText("senha"));
			
			lista.add(ad);
		}
		
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getNumeroCNS() == admin.getNumeroCNS() && lista.get(i).getSenha().equals(admin.getSenha()))
				return lista.get(i);
		}
		
		return null;
	}

}
