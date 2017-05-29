package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoMunicipio;
import br.ueg.unucet.gymsys.Model.Estado;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Municipio;
import br.ueg.unucet.gymsys.Util.Response;

public class MunicipioController extends GenericController<Municipio> {

	private Municipio municipio = new Municipio();
	private Municipio municipioSelecionado = new Municipio();
	private List<?> municipioList;

	@Override
	public Response validar(IModel<?> imodel) {
		Response ret = new Response(true);
		setMunicipio((Municipio) imodel);
		

		return ret;
	}
	
	
	public List<Municipio> listarMunicipio(Estado estado){
		ColecaoMunicipio listaMunicipio = new ColecaoMunicipio();
		try {

			listaMunicipio.setAll(dao.listarMunicpio(estado));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMunicipio.getAll();
	}

	public List<?> getListarTodos(Municipio municipio) {
		ColecaoMunicipio listaMunicipio = new ColecaoMunicipio();
		try {

			listaMunicipio.setAll(dao.listarTodos(municipio));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMunicipio.getAll();
	}

	public Municipio getEntity(String id) {
		Municipio municipio = new Municipio();
		municipio.setIdmunicipio(Integer.parseInt(id));
		ColecaoMunicipio listaMunicipio = new ColecaoMunicipio();
		try {
			listaMunicipio.setAll(dao.pesquisarID(municipio));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		municipio = ((ColecaoMunicipio) listaMunicipio).getIndice(0);
		return municipio;
	}

	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		Response ret = new Response(true);
		return ret;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Municipio getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(Municipio municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}

	

}
