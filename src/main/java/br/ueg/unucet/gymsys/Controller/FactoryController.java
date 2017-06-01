package br.ueg.unucet.gymsys.Controller;


/*
 * Classe respons�vel gerar um objeto do tipo IController a partir de uma String com o nome da entidade
 * Cada entidade ter� seu controle especifico com as devidas adapta��es se necess�rio.
 */


public class FactoryController {

	public static IController getControle(String entidade) { 
		if (entidade.equals("usuario")) return new UsuarioController(); 
		//if (controle.equals("exercicio")) return new ControleExercicio(); 
		
		return null;
	} 
}
