package br.ueg.unucet.gymsys.View;



import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

import br.ueg.unucet.gymsys.Controller.UsuarioController;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Response;


	 

public  class UsuarioViewModel extends ViewModel<Usuario, UsuarioController> {

	private List<?> usuarioList;
    private Usuario selectedUsuario;
 
    
	@Init
	public void Init(){		
		super.init();
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsFuncionario(session)){
			Executions.sendRedirect("/index.zul?msn=2");
		}
		String idusuario;
		idusuario = Executions.getCurrent().getParameter("idusuario");
		if(idusuario!= null){
			System.out.println("idusuario = "+idusuario);
			setEntity(controller.getEntity(idusuario));
			super.keyword = getEntity().getSenha();
		}
	}
	
	@Override
	public UsuarioController getControl() {
		return new UsuarioController();
	}
	@Override
	public Usuario getObject() {
		return new Usuario();
	}

    
    @Command
    @NotifyChange("usuarioList")
    public Response listar() {
    	if(keyword == null){
    		keyword = "";
    	}
    	if(displayEdit){
    		usuarioList = controller.getLstEntitiesAtivos((keyword));
    	}else{
    		usuarioList = controller.getLstEntities((keyword));
    	}
    	
    	return null;
    }

	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarioList(){
        return (List<Usuario>) usuarioList;
    }
	
	public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }
    
    @Override
    @Command
	public Response salvar(IModel<?> imodel) {
    	if ((Integer) getEntity().getPK() == 0) {
			getEntity().setAtivo(true);
		}
    	Response res = super.salvar(imodel);
    	if(res.isValid() && (Integer) getEntity().getPK() == 0){
    		if(getEntity().getTipousuario()==0){
    			setEntity(getControl().pesquisarNomeUsuario(getEntity().getNome()));
    		
    			Session session;
    			session = Executions.getCurrent().getSession();
    			session.setAttribute("aluno.idusuario", (String) String.valueOf(getEntity().getIdusuario()));
    			Executions.sendRedirect("/aluno/frmAluno.zul");
    		}
		} 
		return res;
	}

}