package classes.XML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import classes.DTO.*;
import interfaces.IRegistroVacina;

public class RegistroVacinaXML implements IRegistroVacina {

	List<RegistroVacina> lista = new ArrayList<RegistroVacina>();
	
	final String CAMINHO = "dadosXML/registroVacina.xml";
	
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

	public boolean inserir(RegistroVacina registro) {
		File file = new File(CAMINHO);
		if(file.exists())
			setLista(ler());
		adicionar(registro);
		
		Element config = new Element("registros");
		Document documento = new Document(config);
		
		Element titulo = new Element("titulo");
		titulo.setText("Registros de Vacina");
		
		config.addContent(titulo);
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(int i = 0; i < lista.size(); i++) {
			Element reg = new Element("registro");
			
			reg.setAttribute("id", String.valueOf(lista.get(i).getId()));
			
			Element data = new Element("data");
			data.setText(lista.get(i).getData().format(dataFormato));
			
			Element lote = new Element("lote");
			lote.setText(lista.get(i).getLote().getCodigo());
			
			Element cidadao = new Element("cidadao");
			cidadao.setText(String.valueOf(lista.get(i).getCidadao().getNumeroCNS()));
			
			Element vacinador = new Element("vacinador");
			vacinador.setText(String.valueOf(lista.get(i).getVacinador().getNumeroCNS()));
			
			Element dose = new Element("dose");
			dose.setText(String.valueOf(lista.get(i).getDose()));
			
			Element unidadeSaude = new Element("unidadeSaude");
			unidadeSaude.setText(String.valueOf(lista.get(i).getUnidadeSaude().getId()));
			
			reg.addContent(data);
			reg.addContent(lote);
			reg.addContent(cidadao);
			reg.addContent(vacinador);
			reg.addContent(dose);
			reg.addContent(unidadeSaude);
			config.addContent(reg);
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
	
	public List<RegistroVacina> ler(){
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(CAMINHO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element config = doc.getRootElement();
		List listaRegistro = config.getChildren("registro");
		
		lista = new ArrayList<RegistroVacina>();
		
		DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(Iterator iter = listaRegistro.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			
			RegistroVacina registro = new RegistroVacina();
			registro.setId(Integer.parseInt(element.getAttributeValue("id")));
			registro.setData(LocalDate.parse(element.getChildText("data"), dataFormato));
			
			Lote lote = new Lote(element.getChildText("lote"));
			LoteXML loteXML = new LoteXML();
			lote = loteXML.procurarCodigo(lote);
			
			registro.setLote(lote);
			
			Cidadao cid = new Cidadao(Integer.parseInt(element.getChildText("cidadao")));
			CidadaoXML cidXML = new CidadaoXML();
			cid = cidXML.procurarId(cid);
			
			registro.setCidadao(cid);
			
			Funcionario vac = new Funcionario(Integer.parseInt(element.getChildText("vacinador")));
			FuncionarioXML vacXML = new FuncionarioXML();
			vac = vacXML.procurarId(vac);
			
			registro.setVacinador(vac);
			registro.setDose(Integer.parseInt(element.getChildText("dose")));
			
			UnidadeSaude us = new UnidadeSaude(Integer.parseInt(element.getChildText("unidadeSaude")));
			UnidadeSaudeXML usXML = new UnidadeSaudeXML();
			us = usXML.procurarId(us);
			
			registro.setUnidadeSaude(us);
			
			lista.add(registro);
		}
		
		return lista;
	}

	public List<RegistroVacina> procurarTodosPorId(RegistroVacina registro) {
		lista = new ArrayList<RegistroVacina>();
		
		List<RegistroVacina> listaRegistros = new ArrayList<RegistroVacina>();
		
		File file = new File(CAMINHO);
		if(file.exists()) {
			
			setLista(ler());
			for(int i = 0; i < lista.size(); i++) {
				if(lista.get(i).getCidadao().getNumeroCNS() == registro.getCidadao().getNumeroCNS())
					listaRegistros.add(lista.get(i));
			}
		}
		return listaRegistros;
	}

}