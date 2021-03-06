package br.ueg.unucet.gymsys.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	/**
	 * M�todo que retorna string com a primeira letra em mai�scula, o restante permanece o mesmo.
	 * 
	 * @param str valor
	 * @return valor com a primeira letra em mai�sculo
	 */
	public static String firstToUpperCase(String str) {
		return str.substring(0, 1).toUpperCase().concat(str.substring(1));
	}
	/**
	 * M�todo que retorna string com a primeira letra em min�sculo, o restante permanece o mesmo.
	 * 
	 * @param str valor
	 * @return valor com a primeira letra em min�sculo
	 */
	public static String firstToLowerCase(String str) {
		return str.substring(0, 1).toLowerCase().concat(str.substring(1));
	}
	
	public static String totalLowerCase(String str) {
		return str.toLowerCase();
	}
	
	public static String retirarExtensao(String string) {
		String resposta = null;
		if(string == null) return null;
		String extensao = null;
		int tamanho = string.length();
		extensao = string.substring((tamanho-4), tamanho);
		if(extensao.substring(0, 1).equalsIgnoreCase(".")){
			resposta= extensao;
		}else{
			resposta = string.substring((tamanho-5), tamanho);
		}
		return Utils.totalLowerCase(resposta);
	}
	
	public static Properties getProp() throws IOException {
		 Properties props = new Properties(); 
		 FileInputStream file = new FileInputStream( "C:/Users/jefferson/git/gymsys/src/properties/config.properties"); 
		 props.load(file); 
		 return props; 
	} 
	
	public boolean isEmail(String email) {
		if(email == null){
			return false;
		}
	    System.out.println("Metodo de validacao de email");
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	      System.out.println("O email "+email+" e valido");
	      return true;
	    }
	    else{
	      System.out.println("O E-mail "+email+" � inv�lido");
	      return false;
	    }  
	 }
	
}