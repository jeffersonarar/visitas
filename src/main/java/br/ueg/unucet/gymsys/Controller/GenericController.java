package br.ueg.unucet.gymsys.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ueg.unucet.gymsys.DAO.GenericDAO;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Response;

public abstract class GenericController<Entity> implements IController{

	protected GenericDAO dao  = new GenericDAO();
	private Entity entity;
	protected List<Entity> lstEntities;
	private List<Entity> list;
	private List<Entity> lstCriteria;    

	public void setList(List<Entity> list) {
		this.list = list;
	}

	public abstract Response validar(IModel<?> imodel);
	public abstract Response validarItemUnico(IModel<?> imodel);
	
	public Response salvar(IModel<?> imodel) {
		Response res = validar(imodel);
		if (res.isValid()) {
			return dao.inserir(imodel);
		}
		return res;
	}

	public Response alterar(IModel<?> imodel) {
		Response res = validar(imodel);
		if (res.isValid()) {
			return dao.alterar(imodel);
		}
		return res;
	}

	public Response desativar(IModel<?> imodel) {
		return  dao.excluir(imodel);
	}

	public Response confirmar(IModel<?> imodel) {
		return  dao.confirmar(imodel);
	}
	
	public Response ativar(IModel<?> imodel) {
		return  dao.ativar(imodel);
	}
	public Response listar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Entity> getLstEntities() {
		return lstEntities;
	}
	
	public List<?> getLstEntitiesAtivos(String keyword){
		return lstEntities;
	}

	public List<?> getLstEntities(String keyword) {
		return lstEntities;
	}
	
	public void setLstEntities(List<Entity> lstEntities) {
		this.lstEntities = lstEntities;
	}

	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public List<Entity> getLstCriteria() {
		return lstCriteria;
	}

	public void setLstCriteria(List<Entity> lstCriteria) {
		this.lstCriteria = lstCriteria;
	}



}
