package br.ueg.unucet.gymsys.View.Update;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.FuncaoController;
import br.ueg.unucet.gymsys.Model.Funcao;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;
import br.ueg.unucet.gymsys.View.ViewModel;

@SuppressWarnings("serial")
public class FuncaoUpdateViewModel extends
	    ViewModel<Funcao, FuncaoController> {

	@Wire("#FuncaoUpdate")
	private Window win;
	private String recordMode;
	private Funcao funcaoSelecionado;
	private List<Funcao> funcaoList;
	private List<Funcao> lstFuncao;

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("FuncaoObject") Funcao c1,
			@ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		if (recordMode.equals("EDIT")) {
			setEntity(c1);
		}
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	@NotifyChange("entity")
	@Command
	public void update() throws SQLException {
		Response ret = new Response(true);
		ret = getControl().alterar(getEntity());

		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Função alterada com sucesso!", "Sucess",
					Messagebox.OK, Messagebox.INFORMATION);

			Executions
					.sendRedirect("/paginas/funcao/listarFuncao.zul");
		}

	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	@Override
	public Funcao getObject() {
		return new Funcao();
	}

	@Override
	public FuncaoController getControl() {
		return new FuncaoController();
	}



	@NotifyChange("funcaoList")
	public List<Funcao> getFuncaoList() {
		FuncaoController funcaoController = new FuncaoController();
		funcaoList = funcaoController.getLstEntities();
		return funcaoList;
	}

	public List<Funcao> getLstFuncao() {
		return lstFuncao;
	}

	public void setLstFuncao(List<Funcao> lstFuncao) {
		this.lstFuncao = lstFuncao;
	}

	public Funcao getFuncaoSelecionado() {
		return funcaoSelecionado;
	}

	public void setFuncaoSelecionado(Funcao funcaoSelecionado) {
		this.funcaoSelecionado = funcaoSelecionado;
	}

	public void setFuncaoList(List<Funcao> funcaoList) {
		this.funcaoList = funcaoList;
	}

	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
