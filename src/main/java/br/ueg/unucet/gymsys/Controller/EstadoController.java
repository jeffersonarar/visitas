package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoEstado;
import br.ueg.unucet.gymsys.Model.Estado;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;

public class EstadoController extends GenericController<Estado> {

	Estado estado = new Estado();

	@Override
	public List<Estado> getLstEntities() {
		Estado estado = new Estado();
		ColecaoEstado listaEstado = new ColecaoEstado();
		try {

			listaEstado.setAll(dao.listarTodos(estado));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEstado.getAll();
	}

	@Override
	public List<?> getLstEntities(String keyword) {
		Estado Estado = new Estado();
		ColecaoEstado listaEstado = new ColecaoEstado();
		try {

			listaEstado.setAll(dao.pesquisarNome(Estado, keyword));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEstado.getAll();
	}

	public List<?> listarTodos(Estado Estado) {
		ColecaoEstado listaEstado = new ColecaoEstado();
		try {
			listaEstado.setAll(dao.listarTodos(Estado));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEstado.getAll();
	}

	@Override
	public Response validar(IModel<?> imodel) {
		Response ret = new Response(true);
		setEstado((Estado) imodel);


		return ret;
	}

	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		return null;
	}

	public Estado getEntity(String id) {
		Estado estado = new Estado();
		estado.setIdestado(Integer.parseInt(id));
		ColecaoEstado listaEstado = new ColecaoEstado();
		try {
			listaEstado.setAll(dao.pesquisarID(estado));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		estado = ((ColecaoEstado) listaEstado).getIndice(0);
		return estado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
