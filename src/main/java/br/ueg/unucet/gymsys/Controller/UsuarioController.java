package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoUsuario;
import br.ueg.unucet.gymsys.DAO.UsuarioDAO;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Mensagembox;
import br.ueg.unucet.gymsys.Util.Response;

public class UsuarioController extends GenericController<Usuario> {
	private Mensagembox msgbox = new Mensagembox();
	
	private Usuario Usuario = new Usuario();
	
	@Override
	public List<?> getLstEntities(String keyword) {
		Usuario usuario = new Usuario();
		ColecaoUsuario clUsuario = new ColecaoUsuario();
		try {
			clUsuario.setAll(dao.pesquisarNome(usuario, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clUsuario.getAll();
	}
	
	@Override
	public List<?> getLstEntitiesAtivos(String keyword) {
		Usuario usuario = new Usuario();
		ColecaoUsuario clUsuario = new ColecaoUsuario();
		try {
			clUsuario.setAll(dao.pesquisarNomeAtivo(usuario, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clUsuario.getAll();
	}



	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	
	public Usuario getEntity(String id) {
		Usuario usuario = new Usuario();
		usuario.setIdusuario(Integer.parseInt(id));
		ColecaoUsuario clUsuario = new ColecaoUsuario();
		try {
			clUsuario.setAll(dao.pesquisarID(usuario));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuario = ((ColecaoUsuario) clUsuario).getIndice(0);
		return usuario;
	}

	public Usuario logar(String nome, String senha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ColecaoUsuario clUsuario = new ColecaoUsuario();
		try {
			clUsuario.setAll(usuarioDAO.logar(nome, senha));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ((ColecaoUsuario) clUsuario).getIndice(0);
	}
	
	public Usuario pesquisarNomeUsuario(String nome) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ColecaoUsuario clUsuario = new ColecaoUsuario();
		try {
			clUsuario.setAll(usuarioDAO.pesquisarNomeUsuario(nome));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ((ColecaoUsuario) clUsuario).getIndice(0);
	}

	@Override
	public Response validar(IModel<?> imodel) {
		setUsuario((br.ueg.unucet.gymsys.Model.Usuario) imodel);
		Response res = new Response(true,"", null);
		if(getUsuario().getNome() == null){
			return  new Response(false, "Campo nome é obrigatório!", TypeMessage.AVISO);
		}
		if(getUsuario().getSenha() == null){
			return  new Response(false, "Campo senha é obrigatório!", TypeMessage.AVISO);
		}
		return res;
	}	
	
	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		Response res = new Response(true,"", null);
		setUsuario((br.ueg.unucet.gymsys.Model.Usuario) imodel);
		String[] valores = new String[1];
		String[] nomesVariaveis = new String[1];
		valores[0] = String.valueOf(getUsuario().getNome());
		nomesVariaveis[0] = "nome";
		try {
			res = dao.validarItemUnico(imodel, valores, nomesVariaveis);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Response salvar(IModel<?> imodel) {
		Response res = super.salvar(imodel);
		if(res.isValid())return res;
		else{
			res.setMensagem(res.getMensagem() + "Este Nome já esta sendo utilizado...");
		}
		return res;
	}
	
	@Override
	public Response alterar(IModel<?> imodel) {
		Response res = super.alterar(imodel);
		if (res.isValid())return res;
		else{
			res.setMensagem(res.getMensagem() + "Este Nome já esta sendo utilizado...");
		}
		return res;
	}
}