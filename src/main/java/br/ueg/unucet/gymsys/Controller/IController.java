package br.ueg.unucet.gymsys.Controller;

import javax.swing.text.html.parser.Entity;

/*
 * Classe responsável por gerar uma interface de acesso a todos os controladores disponiveis pela aplicação...
 */

import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;

public interface IController {

	public Response salvar(IModel<?> imodel);
	
	public Response alterar(IModel<?> imodel);
	
	public Response listar(IModel<?> imodel);
	
	public Response desativar(IModel<?> imodel);
	
	public Response confirmar(IModel<?> imodel);
	
}
