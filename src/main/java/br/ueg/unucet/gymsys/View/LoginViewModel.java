package br.ueg.unucet.gymsys.View;

import java.io.IOException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.UsuarioController;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Response;

public class LoginViewModel extends ViewModel<Usuario, UsuarioController>{
	
	private String nome;
	
	private String senha;
	

	@Wire("#imagens")
	private org.zkoss.zul.Image imagens;
	
	@Wire("#imagem")
	private Window imagem;
	
	@Init
	public void Init() throws IOException{	
		super.init();
		Session session;
		session = Executions.getCurrent().getSession();
		if(session.getAttribute("status") == null){
			session.setAttribute("status", "offline");
		}
	}


	@Command
	public void logar(){
		Usuario usuariologado = getController().logar(nome, senha);
		if(usuariologado== null){
			msgbox.mensagemError("Por gentileza informe novamente Usuario não foi encontrado");
			System.out.println("nao conseguiu logar");
		}else{
			Executions.getCurrent().getSession().setAttribute("id", usuariologado.getIdusuario());
			if(usuariologado.getTipousuario() == 0 ){
				Executions.getCurrent().getSession().setAttribute("status", "onlineAluno");
				Executions.sendRedirect("/indexAluno.zul?msn=1");
			}
			if(usuariologado.getTipousuario() == 1){
				Executions.getCurrent().getSession().setAttribute("status", "onlineFuncionario");
				Executions.sendRedirect("/indexFuncionario.zul?msn=1");
			}
		}
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public UsuarioController getControl() {
		return new UsuarioController();
	}
	@Override
	public Usuario getObject() {
		return new Usuario();
	}

	
	public org.zkoss.zul.Image getImagens() {
		return imagens;
	}

	public void setImagens(org.zkoss.zul.Image imagens) {
		this.imagens = imagens;
	}

	public Window getImagem() {
		return imagem;
	}

	public void setImagem(Window imagem) {
		this.imagem = imagem;
	}
	
	@Command
	public void logar2() {
		Window window = (Window)Executions.createComponents(
                "/log.zul", null, null);
        window.doModal();
    }

	public Response listar() {
		// TODO Auto-generated method stub
		return null;
	}


	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}
}
