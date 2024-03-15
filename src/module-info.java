/**
 * 
 */
/**
 * 
 */
module ControleVacinasBD {
	requires java.sql;
	requires com.google.gson;
	requires org.jdom2;
	opens classes.DTO to com.google.gson;
}