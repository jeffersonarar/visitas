package br.ueg.unucet.gymsys.Controller;


/*
 * Classe responsável gerar um objeto do tipo IController a partir de uma String com o nome da entidade
 * Cada entidade terá seu controle especifico com as devidas adaptações se necessário.
 */


public class FactoryController {

	public static IController getControle(String entidade) { 
		if (entidade.equals("usuario")) return new UsuarioController(); 
		//if (controle.equals("exercicio")) return new ControleExercicio(); 
		
		return null;
	} 
}
