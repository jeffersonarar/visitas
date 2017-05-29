package br.ueg.unucet.gymsys.View;

import br.ueg.unucet.gymsys.Model.IModel;

import br.ueg.unucet.gymsys.Util.Response;

public interface IViewModel {

	public Response salvar(IModel<?> imodel);
	
	public Response desativar(IModel<?> imodel);
	
	public Response listar();
	
	public void telaAlterar();

}
