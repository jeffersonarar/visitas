package br.ueg.unucet.gymsys.View;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import br.ueg.unucet.gymsys.Controller.FuncaoController;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Funcao;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;

@SuppressWarnings("serial")
public class FuncaoViewModel extends ViewModel<Funcao,FuncaoController> {
	

	private List<?> funcaoList;
    private Funcao selectedEntity;
  
	


	@Init
	public void Init(){	
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsFuncionario(session)){
			Executions.sendRedirect("/index.zul?msn=2");
		}
		super.init();
		String id;
		id = Executions.getCurrent().getParameter("idlocal");
		if(id!= null){
			System.out.println("id = "+id);
			setEntity(controller.getEntity(id));
	
		}
	}
	
	@Override
	public FuncaoController getControl() {
		return new FuncaoController();
	}
	@Override
	public Funcao getObject() {
		return new Funcao();
	}
	
	
    @Command
    @NotifyChange("funcaoList")
    public Response listar() {
    	if(keyword == null){
    		keyword = "";
    	}
    	if(displayEdit){
    		funcaoList = controller.getLstEntitiesAtivos((keyword));
    	}else{
    		funcaoList = controller.getLstEntities((keyword));
    	}
    	return null;
    }

    @Command
	public void telaAlterar() {
		
		if (getSelectedEntity() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione algum item para alterar!");
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("FuncaoObject", this.selectedEntity);
			map.put("recordMode", "EDIT");
		//	setFuncaoSelectedIndex(funcaoList.indexOf(selectedEntity));
			Executions.createComponents("funcao_component.zul", null, map);
			// setItemSelected(null);
		}
	
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("funcaoList")
	public Response deletar() {

		Response ret = new Response(true);
		if (getSelectedEntity() == null) {
			msgbox.mensagem(br.ueg.unucet.gymsys.Enum.TypeMessage.ERROR, "Selecione um item para ser deletado!");
		} else {
			String str = "Deseja deletar a funcao \""
					+ getSelectedEntity().getNome() + "\"?";
			Messagebox.show(str, "Confirm", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {

									getControl().desativar(getSelectedEntity());
									msgbox.mensagem(TypeMessage.SUCESSO, "Função deletado com sucesso!");
									setSelectedEntity(null);;
								}
							}
						}
					});

		}
		return ret;
	}
	
	@Override
	@Command
	public Response salvar(IModel<?> imodel) {
		if((Integer)getEntity().getPK() == 0){
			getEntity().setAtivo(true);
		}
	
	    return super.salvar(getEntity());
	}
	   
    @Command
    public void closeWindows() {
    	Executions.sendRedirect("/index.zul");
    }
    


	@Command
	public void cancel() {
		Executions.sendRedirect("/paginas/initial_page.zul");
	}
	

    
	public Response desativar(IModel<?> imodel) {
		return null;
	}
	
	public void setSelectedEntity(Funcao selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
	
    public Funcao getSelectedEntity() {
        return selectedEntity;
    }
    
	public List<?> getLocalList() {
		return funcaoList;
	}

	public List<?> getFuncaoList() {
		return funcaoList;
	}

	public void setFuncaoList(List<?> funcaoList) {
		this.funcaoList = funcaoList;
	}

	


}
