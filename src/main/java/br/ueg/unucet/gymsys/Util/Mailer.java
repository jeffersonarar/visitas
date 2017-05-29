package br.ueg.unucet.gymsys.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.ueg.unucet.gymsys.Model.Visita;

public class Mailer {

	public void enviarEmail(String assunto,  Visita reserva, String texto){
	    
		HtmlEmail email = new HtmlEmail();	

	
		try {
			email.setDebug(true);  
	        email.setHostName("smtp.gmail.com");  
	        email.setAuthentication("gapansti@gmail.com","muitoobvia");  
	        email.setSSL(true);  
	        email.setFrom("gapansti@gmail.com"); //aqui necessita ser o email que voce fara a autenticacao  //aqui necessita ser o email que voce fara a autenticacao  
		        email.setSubject(assunto); // Assunto do e-mail
		   
		    email.setHtmlMsg(""
		    		+ "<html>"
					+ 	"<head>"
					+ 		"<style type='text/css'>"
					+ 			"a:link, a:visited {"
					+ 				"color: blue; text-decoration:none;cursor: auto;"
					+ 			"}"
					+ 			"a:link:active, a:visited:active {"
					+ 				"color: blue; text-decoration:none;"
					+ 			"}"
					+ 		"</style>"
					+ 	"</head>"
					+ 	"<body>"
					+ 		"<table style='margin:0 auto; width:600px; height:100px; position:relative; top:20px; border: 1px solid #D3D3D3; font-family: Arial, Helvetica, sans-serif; font-size:14px; color:#4f4f4f; line-height:25px; text-align: justify;' border='0'>"
					+ 			"<tr style='height:200px'>"
					+ 				"<td style='padding:10px 20px 10px 10px'>"
					+ 					 texto 
					+ 					"<br/><br/>"
					+ 					"<b>SISTEMA DE RESERVAS.</b>"
					+ 					"<br/><br/>"
					+ 					"<i>Por favor não responda essa mensagem. Esse é um e-mail automático.</i>"
					+ 					"<br/><br/>"
					+ 				"</td>"
					+ 			"</tr>"
					+ 		"</table>"
					+ 	"</body>"
					+ "</html>"); // Corpo do Email		             
		    email.addTo(reserva.getEmail());
		    email.send();
		} catch (EmailException e) {
		    e.printStackTrace();
		}
	}
	
	public boolean validatorEmail(String email){
		 if ((email == null) || (email.trim().length() == 0))
	            return false;

	        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    
	}
}