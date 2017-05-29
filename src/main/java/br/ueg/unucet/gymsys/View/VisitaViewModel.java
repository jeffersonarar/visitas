package br.ueg.unucet.gymsys.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


import br.ueg.unucet.gymsys.Controller.EstadoController;
import br.ueg.unucet.gymsys.Controller.FuncaoController;
import br.ueg.unucet.gymsys.Controller.MunicipioController;
import br.ueg.unucet.gymsys.Controller.VisitaController;
import br.ueg.unucet.gymsys.Enum.Agendadopor;
import br.ueg.unucet.gymsys.Enum.Horas;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Estado;
import br.ueg.unucet.gymsys.Model.Funcao;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Municipio;
import br.ueg.unucet.gymsys.Model.Visita;
import br.ueg.unucet.gymsys.Util.Mailer;
import br.ueg.unucet.gymsys.Util.MensagensMailer;
import br.ueg.unucet.gymsys.Util.ParametrosWindow;
import br.ueg.unucet.gymsys.Util.Response;
import br.ueg.unucet.gymsys.View.zk_calendar.DemoCalendarEvent;

public class VisitaViewModel extends ViewModel<Visita, VisitaController> {

	private List<?> listVisita;

	private List<?> funcaoList;

	private List<Horas> horasList = new ArrayList<Horas>();

	private Horas horasinicioselecionada;
	private Horas horasfimselecionada;

	private List<Agendadopor> agendadoporList = new ArrayList<Agendadopor>();
	private  Agendadopor agendadoporSelecionada;
	
	private Funcao funcaoSelecionado;


	private Boolean confirmado;

	Mailer mailer = new Mailer();
	MensagensMailer msgMailer = new MensagensMailer();

	@SuppressWarnings("unused")
	private DemoCalendarEvent calendarEventData = new DemoCalendarEvent();

	
	private Estado estadoSelecionada;
	private List<?> estadoList;
	Estado estado = new Estado();
	EstadoController estadoController = new EstadoController();
	
	
	private Municipio municipioSelecionada;
	private List<Municipio> municipioList;
	Municipio municipio = new Municipio();
	MunicipioController municipioController = new MunicipioController();
	
	
	private boolean visible = false;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("EntityObject") Visita e1,
			@ExecutionArgParam("param") ParametrosWindow p, @ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsFuncionario(session)){
			Executions.sendRedirect("/index.zul?msn=2");
		}
		super.init();
		String id;
		id = Executions.getCurrent().getParameter("idfuncao");
		if(id!= null){
			System.out.println("id = "+id);
			setEntity(controller.getEntity(id));
	
		}
		if (e1 != null) {
			if (recordMode.equals("EDIT")) {

				setWin((Window) view.getFellow("idWindowAlterar"));
				this.selectedEntity = e1;
				setEntity(e1);
			}
		}

		super.init();

		if (p != null) {
			setVisible(true);
			getEntity().setData_inicio(p.getData());
		}

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


	@SuppressWarnings("unchecked")
	@NotifyChange("funcaoList")
	public List<Funcao> getFuncaoList() {
		FuncaoController FuncaoController = new FuncaoController();
		funcaoList = FuncaoController.getLstEntities("");
		return (List<Funcao>) funcaoList;
	}

	@Command
	public void voltar() {
		Executions.sendRedirect("calendario.zul");
	}


	@SuppressWarnings("unchecked")
	@NotifyChange("funcaoList")
	public List<Funcao> getPostoList() {
		FuncaoController funcaoController = new FuncaoController();
		funcaoList = funcaoController.getLstEntities("");
		return (List<Funcao>) funcaoList;
	}

	public void abrirWindow(ParametrosWindow p) {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		p.setVisible(true);
		map.put("param", p);
		Executions.createComponents("/paginas/visita/visitas.zul", null, map);
	}

	@Command
	@NotifyChange("listVisita")
	public Response listar() {

		setlistVisita(controller.getLstEntities((confirmado)));
		return null;
	}

	
	
	@Command
	public Response telaDetalhar() {
		Response ret = new Response(true);
		if (getSelectedEntity() == null) {
			Messagebox.show("Selecione algum item!", "Error",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("EntityObject", this.selectedEntity);
			map.put("recordMode", "EDIT");
			Executions.createComponents("visita_component.zul", null, map);
			// setItemSelected(null);
		}
		return ret;
	}
	
	
	@Command
	public Response telaReagendar() {
		Response ret = new Response(true);
		if (getSelectedEntity() == null) {
			Messagebox.show("Selecione algum item!", "Error",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("EntityObject", this.selectedEntity);
			map.put("recordMode", "EDIT");
			Executions.createComponents("reagendamento_component.zul", null, map);
			// setItemSelected(null);
		}
		return ret;
	}
	
	@Command
	public Response salvar(IModel<?> imodel) {
		Response res;
		/*
		 * getEntity().setAgendadopor(getAgendadoselecionada().getId());
		 * getEntity().setData_fim(new Date());
		 * getEntity().setHora_visita(getHorariovisitaselecionada().getDescricao
		 * ());
		 */
			getEntity().setAgendadopor(getAgendadoporSelecionada().getDescricao());
			getEntity().setHora_inicio(getHorasinicioselecionada().getDescricao());
			getEntity().setFuncao(getFuncaoSelecionado());
			getEntity().setAtivo(true);
			getEntity().setConfirmado(true);
			getEntity().setData_cadastro(new Date());
			getEntity().setHora_inicio(getHorasinicioselecionada().getDescricao());
			super.salvar(getEntity());
		
		// voltar();
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("listVisita")
	public Response confirmar() {

		Response ret = new Response(true);
		if (this.selectedEntity == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione um item para ser confirmado!");
		} else {
			String str = "Deseja confirmar reserva do? \"" + getSelectedEntity().getFuncao().getNome() + " - "
					+ getSelectedEntity().getAgendadopor() + "\"?";
			Messagebox.show(str, "Confirm", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {

				public void onEvent(Event event) throws Exception {
					if (event.getName().equals("onNo")) {
						mailer.enviarEmail("Confirmação da reserva", getSelectedEntity(),
								msgMailer.mensagemAoRecusar(getSelectedEntity()));
						getControl().desativar(getSelectedEntity());
						return;
					} else {
						if (event.getName().equals("onYes")) {

							getControl().confirmar(getSelectedEntity());
							mailer.enviarEmail("Confirmação da reserva", getSelectedEntity(),
									msgMailer.mensagemAoConfirmar(getSelectedEntity()));
							msgbox.mensagem(TypeMessage.SUCESSO, "Reserva confirmada com sucesso!");
							setSelectedEntity(null);
						}
					}
				}
			});

		}
		return ret;
	}

	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visita getObject() {
		return new Visita();
	}

	@Override
	public VisitaController getControl() {
		return new VisitaController();
	}

	public void setlistVisita(List<?> listVisita) {
		this.listVisita = listVisita;
	}


	public Funcao getFuncaoSelecionado() {
		return funcaoSelecionado;
	}

	public void setFuncaoSelecionado(Funcao funcaoSelecionado) {
		this.funcaoSelecionado = funcaoSelecionado;
	}




	public List<?> getListVisita() {
		return listVisita;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public void setHorasList(List<Horas> horasList) {
		this.horasList = horasList;
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

	public void setAgendadoporList(List<Agendadopor> agendadoporList) {
		this.agendadoporList = agendadoporList;
	}

	public Agendadopor getAgendadoporSelecionada() {
		return agendadoporSelecionada;
	}

	public void setAgendadoporSelecionada(Agendadopor agendadoporSelecionada) {
		this.agendadoporSelecionada = agendadoporSelecionada;
	}

	public void setListVisita(List<?> listVisita) {
		this.listVisita = listVisita;
	}

	public void setFuncaoList(List<?> funcaoList) {
		this.funcaoList = funcaoList;
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

	public void setEstadoList(List<?> estadoList) {
		this.estadoList = estadoList;
	}

	public void setMunicipioList(List<Municipio> municipioList) {
		this.municipioList = municipioList;
	}

}
