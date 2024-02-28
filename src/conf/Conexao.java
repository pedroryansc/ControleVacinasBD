package conf;

import java.sql.DriverManager;
import java.sql.Connection;

public class Conexao {
	
	final static String NOME_DO_BANCO = "controlevacinas";
	
	public static Connection conectar() {
		try {
			String url = "jdbc:mysql://localhost/" + NOME_DO_BANCO;
			return DriverManager.getConnection(url, "root", "");
		} catch(Exception e) {
			System.out.println("Erro: " + e.toString());
			e.printStackTrace();
			return null;
		}
	}
	
}