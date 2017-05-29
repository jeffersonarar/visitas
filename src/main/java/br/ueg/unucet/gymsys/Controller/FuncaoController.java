package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoFuncao;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Funcao;
import br.ueg.unucet.gymsys.Util.Response;

public class FuncaoController extends GenericController<Funcao> {
	
		private Funcao funcao = new Funcao();
		
		@Override
		public List<?> getLstEntities(String keyword) {
			ColecaoFuncao listafuncao = new ColecaoFuncao();
			try {
				listafuncao.setAll(dao.pesquisarNome(funcao, keyword));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listafuncao.getAll();
		}
		
		public List<?> getLstCriteria(String keyword) {
			ColecaoFuncao listafuncao = new ColecaoFuncao();
			try {
				listafuncao.setAll(dao.pesquisarCriterio(funcao, Integer.parseInt(keyword)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listafuncao.getAll();
		}
		
		@Override
		public List<?> getLstEntitiesAtivos(String keyword) {
			ColecaoFuncao listafuncao = new ColecaoFuncao();
			try {
				listafuncao.setAll(dao.pesquisarNomeAtivo(funcao, keyword));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listafuncao.getAll();
		}
		

		public Funcao getfuncao() {
			return this.funcao;
		}

		public void setfuncao(Funcao funcao) {
			this.funcao = funcao;
		}
		
		public Funcao getEntity(String id) {
			Funcao funcao = new Funcao();
			funcao.setIdfuncao(Integer.parseInt(id));
			ColecaoFuncao listafuncao = new ColecaoFuncao();
			try {
				listafuncao.setAll(dao.pesquisarID(funcao));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			funcao = ((ColecaoFuncao) listafuncao).getIndice(0);
			return funcao;
		}
		
		

		@Override
		public Response validar(IModel<?> imodel) {
		setfuncao((br.ueg.unucet.gymsys.Model.Funcao) imodel);
			
			Response res = new Response(true,"", null);

			if(getfuncao().getNome() == null){
				return  new Response(false, "Campo obrigatório não preenchido.", TypeMessage.AVISO);
			}

		/*	if(getfuncao().getDescricao()== null){
				return  new Response(false, "Campo obrigatório não preenchido.", TypeMessage.AVISO);
			}
			if(getfuncao().getDescricao().length()>499){
				return  new Response(false, "Campo Descrição deve conter menos de 500 caracteres!\n\n"
											+ "No momento esta com "+ getfuncao().getDescricao().length()
											+" caracteres.", TypeMessage.AVISO);
			}*/
			if(getfuncao().getNome().length()<3 ||  getfuncao().getNome().length()>120){
				return  new Response(false, "Tamanho do nome informado não é adequado o nome deve conter no mínimo 3 \n no máximo 120 caracteres. Exercício não foi Salvo...", TypeMessage.AVISO);
			}
			//“Exercício já cadastrado no sistema...”
			return res;
		}	
		
		@Override
		public Response validarItemUnico(IModel<?> imodel) {
			Response res = new Response(true,"", null);
			return res;
		}
	}