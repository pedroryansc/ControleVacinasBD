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
import interfaces.IFuncionario;

public class FuncionarioXML implements IFuncionario {

	List<Funcionario> lista = new ArrayList<Funcionario>();
	
	final String CAMINHO = "dadosXML/funcionario.xml";
	
	public Funcionario procurarIdSenha(Funcionario func) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaAdmin = config.getChildren("funcionario");
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(Iterator iter = listaAdmin.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			Funcionario fu = new Funcionario();
			fu.setNumeroCNS(Integer.parseInt(element.getAttributeValue("numeroCNS")));
			fu.setNome(element.getChildText("nome"));
			fu.setDataNascimento(LocalDate.parse(element.getChildText("dataNascimento"), dataFormato));
			fu.setCpf(element.getChildText("cpf"));
			fu.setSenha(element.getChildText("senha"));
			
			lista.add(fu);
		}
		
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getNumeroCNS() == func.getNumeroCNS() && lista.get(i).getSenha().equals(func.getSenha()))
				return lista.get(i);
		}
		
		return null;
	}

	public List<Funcionario> procurarTodosPorIdUS(Funcionario func) {
		return null;
	}

}