package br.ueg.unucet.gymsys.View;

import java.sql.SQLException;
import java.util.ArrayList;
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

import br.ueg.unucet.gymsys.Controller.EstadoController;
import br.ueg.unucet.gymsys.Controller.FuncaoController;
import br.ueg.unucet.gymsys.Controller.MunicipioController;
import br.ueg.unucet.gymsys.Controller.VisitaController;
import br.ueg.unucet.gymsys.Enum.Agendadopor;
import br.ueg.unucet.gymsys.Enum.Horas;
import br.ueg.unucet.gymsys.Model.Estado;
import br.ueg.unucet.gymsys.Model.Funcao;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Municipio;
import br.ueg.unucet.gymsys.Model.Visita;
import br.ueg.unucet.gymsys.Util.Response;

@SuppressWarnings("serial")
public class VisitaUpdateViewModel extends ViewModel<Visita, VisitaController> {

	@Wire("#CargoUpdate")
	private Window win;
	private String recordMode;

	private List<?> funcaoList;

	private Funcao funcaoSelecionado;
	
	private List<Horas> horasList = new ArrayList<Horas>();

	private Horas horasinicioselecionada;
	private Horas horasfimselecionada;

	private List<Agendadopor> agendadoporList = new ArrayList<Agendadopor>();
	private  Agendadopor agendadoporSelecionada;
	
	private Estado estadoSelecionada;
	private List<?> estadoList;
	Estado estado = new Estado();
	EstadoController estadoController = new EstadoController();
	
	
	private Municipio municipioSelecionada;
	private List<Municipio> municipioList;
	Municipio municipio = new Municipio();
	MunicipioController municipioController = new MunicipioController();

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("EntityObject") Visita c1,
			@ExecutionArgParam("recordMode") String recordMode) throws CloneNotSupportedException {
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
	
	

	@NotifyChange("estadoList")
	public List<?> getEstadoList() {
		estadoList = estadoController.listarTodos(estado);
		return estadoList;
	}
	
	
	@Command
	@NotifyChange("municipioList")
	public List<Municipio> getMunicipioList() {
		municipioList = new ArrayList<Municipio>();
		if(municipio != null){
			System.out.println("x -- x");
			municipioList = municipioController.listarMunicipio(estadoSelecionada);
			return municipioList;
		}else{
			System.out.println("x -- x");
			 municipioList = new ArrayList<Municipio>();
			 return municipioList;
		}
	}

	
	public List<Horas> getHorasList() {
		for (Horas horas2 : Horas.values()) {
			horasList.add(horas2);
		}
		return horasList;
	}
	@NotifyChange("agendadoporList")
	public List<Agendadopor> getAgendadoporList() {
		for (Agendadopor object : Agendadopor.values()) {
			agendadoporList.add(object);
		}
		return agendadoporList;
	}

	@NotifyChange("entity")
	@Command
	public Response update() throws SQLException {
		Response ret = new Response(true);
		getEntity().setAgendadopor(getAgendadoporSelecionada().getDescricao());
		getEntity().setConfirmado(false);
		ret = getControl().alterar(getEntity());

		if (ret.isValid()) {
			Messagebox.show("Visita cancelado com sucesso!", "Sucess", Messagebox.OK, Messagebox.INFORMATION);

			Executions.sendRedirect("/paginas/visita/listarVisita.zul");
		}else{
			Messagebox.show("Erro ao cancelar!", "Error", Messagebox.OK, Messagebox.INFORMATION);
		}
		return ret;
	}

	
	@NotifyChange("entity")
	@Command
	public Response updateReagendamento() throws SQLException {
		Response ret = new Response(true);
		getEntity().setHora_inicio(getHorasinicioselecionada().getDescricao());
		getEntity().setAgendadopor(getAgendadoporSelecionada().getDescricao());
		ret = getControl().alterar(getEntity());

		if (ret.isValid()) {
			Messagebox.show("Visita reagendada com sucesso!", "Sucess", Messagebox.OK, Messagebox.INFORMATION);

			Executions.sendRedirect("/paginas/visita/listarVisita.zul");
		}else{
			Messagebox.show("Erro ao cancelar!", "Error", Messagebox.OK, Messagebox.INFORMATION);
		}
		return ret;
	}
	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visita getObject() {
		// TODO Auto-generated method stub
		return new Visita();
	}

	@Override
	public VisitaController getControl() {
		// TODO Auto-generated method stub
		return new VisitaController();
	}

	@SuppressWarnings("unchecked")
	@NotifyChange("funcaoList")
	public List<Funcao> getFuncaoList() {
		FuncaoController funcaoController = new FuncaoController();
		funcaoList = funcaoController.getLstEntities("");
		return (List<Funcao>) funcaoList;
	}

	public Funcao getFuncaoSelecionado() {
		return funcaoSelecionado;
	}

	public void setFuncaoSelecionado(Funcao funcaoSelecionado) {
		this.funcaoSelecionado = funcaoSelecionado;
	}

	public void setFuncaoList(List<?> funcaoList) {
		this.funcaoList = funcaoList;
	}

	public Horas getHorasinicioselecionada() {
		return horasinicioselecionada;
	}

	public void setHorasinicioselecionada(Horas horasinicioselecionada) {
		this.horasinicioselecionada = horasinicioselecionada;
	}

	public Horas getHorasfimselecionada() {
		return horasfimselecionada;
	}

	public void setHorasfimselecionada(Horas horasfimselecionada) {
		this.horasfimselecionada = horasfimselecionada;
	}

	public Agendadopor getAgendadoporSelecionada() {
		return agendadoporSelecionada;
	}

	public void setAgendadoporSelecionada(Agendadopor agendadoporSelecionada) {
		this.agendadoporSelecionada = agendadoporSelecionada;
	}

	public Estado getEstadoSelecionada() {
		return estadoSelecionada;
	}

	public void setEstadoSelecionada(Estado estadoSelecionada) {
		this.estadoSelecionada = estadoSelecionada;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public EstadoController getEstadoController() {
		return estadoController;
	}

	public void setEstadoController(EstadoController estadoController) {
		this.estadoController = estadoController;
	}

	public Municipio getMunicipioSelecionada() {
		return municipioSelecionada;
	}

	public void setMunicipioSelecionada(Municipio municipioSelecionada) {
		this.municipioSelecionada = municipioSelecionada;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public MunicipioController getMunicipioController() {
		return municipioController;
	}

	public void setMunicipioController(MunicipioController municipioController) {
		this.municipioController = municipioController;
	}

	public void setHorasList(List<Horas> horasList) {
		this.horasList = horasList;
	}

	public void setAgendadoporList(List<Agendadopor> agendadoporList) {
		this.agendadoporList = agendadoporList;
	}

	public void setEstadoList(List<?> estadoList) {
		this.estadoList = estadoList;
	}

	public void setMunicipioList(List<Municipio> municipioList) {
		this.municipioList = municipioList;
	}

}
