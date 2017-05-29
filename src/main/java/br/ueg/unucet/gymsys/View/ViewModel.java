package br.ueg.unucet.gymsys.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.GenericController;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Util.Mensagembox;
import br.ueg.unucet.gymsys.Util.Response;
import br.ueg.unucet.gymsys.Util.Utils;
import bsh.util.Util;

@SuppressWarnings( "rawtypes")
public abstract class ViewModel<M extends Model, C extends GenericController<M>> implements IViewModel {

	private M entity;
	private List<C> lstEntities;
	protected C controller;
	protected M selectedEntity;
	protected String keyword;
	Mensagembox msgbox = new Mensagembox();
	protected boolean displayEdit = true;
	private Window windowDesativar;
	private String recordMode;
	private Window win;
	protected Integer curSelectedEntity;
	public abstract M getObject();
	public abstract C getControl();

	@Init
	public void init() {
		setEntity(getObject());
		entity = getEntity();
		setController(getControl());
		String msn;
		msn = String.valueOf(Executions.getCurrent().getParameter("msn"));
		if (msn != null && !msn.equalsIgnoreCase("null")) {
			MostrarMensagem(msn);
		}
		String url = null;
		url = Executions.getCurrent().getParameter("action");
		if(url != null && !url.equalsIgnoreCase("null") ){
			if(url.equalsIgnoreCase("1")){
				listar();
			}
			if(url.equalsIgnoreCase("2")){
				logout();
			}
		}
	}

	@Command
	public Response salvar(IModel<?> imodel) {
		Response res = new Response(false, "");
		if ((Integer) getEntity().getPK() != 0) {
			res = getControl().alterar(getEntity());
		} else {
			getEntity().setAtivo(true);
			res = getControl().salvar(getEntity());
		}
		msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
		return res;
	}

	@Command
	public void perguntarDesativar(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		if(getSelectedEntity()!=null){
			setWindowDesativar((Window) view.getFellow("windowDesativar"));
			
			getWindowDesativar().setVisible(true);
			getWindowDesativar().doModal();
			
		}else{
			Response res = new Response(false, "Não foi selecionado nenhum item!", TypeMessage.ERROR);
			msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
		}
		
	}

	@Command
	public Response desativar(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
		setWindowDesativar((Window) view.getFellow("windowDesativar"));
		getWindowDesativar().setVisible(false);
		Response res = new Response(false, "");
		res = getControl().desativar(getSelectedEntity());
		return null;
	}

	@Command
	public Response ativar() {
		Response res = new Response(false, "");
		res = getControl().ativar(getEntity());
		System.out.println(res);
		Executions.sendRedirect("/" + getEntity().getTableName() + "/listar"
				+ getEntity().getTableName() + ".zul");
		return null;
	}
	
	@Command
	public void fecharWin() {
		Executions.sendRedirect("/paginas/visita/listarVisita.zul");
	}

	@Command
	public void telaAlterar() {
		
		if(getSelectedEntity()!=null){
			Executions.sendRedirect("/" + getSelectedEntity().getTableName()
					+ "/cadastrar.zul?"
					+ getSelectedEntity().getPKName() + "="
					+ getSelectedEntity().getPK());
			
		}else{
			Response res = new Response(false, "Não foi selecionado nenhum item!", TypeMessage.ERROR);
			msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
		}
	
	}

	public void gerarRelatorio(String nomeArquivoJrxml, List<?> lista,
			String nomeRelatorio) throws JRException {
		try {
			JRPdfExporter exporter = new JRPdfExporter();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperReport jr = JasperCompileManager.compileReport(Executions
					.getCurrent().getSession().getWebApp()
					.getRealPath("/relatorios/" + nomeArquivoJrxml + ".jrxml"));
			JasperPrint jp = JasperFillManager.fillReport(jr, null,
					new JRBeanCollectionDataSource(lista));
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					outputStream);
			exporter.setParameter(
					JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
					Boolean.TRUE);
			exporter.exportReport();
			Filedownload.save(
					new ByteArrayInputStream(outputStream.toByteArray()),
					"application/pdf", "" + nomeRelatorio + ".pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	@NotifyChange({ "displayEdit" })
	public void setDisplayEdit(boolean displayEdit) {
		this.displayEdit = displayEdit;
	}

	@Command
	public void cancelar(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		setWindowDesativar((Window) view.getFellow("windowDesativar"));
		getWindowDesativar().setVisible(false);
	}

	protected boolean verificarLoginIsAluno(Session session) {
		if (session.getAttribute("status") == null) {
			return false;
		} else {
			String status = (String) session.getAttribute("status");
			if (status.equalsIgnoreCase("offline"))
				return false;
			if (status.equalsIgnoreCase("onlineAluno"))
				return true;
			else
				return false;
		}
	}

	protected boolean verificarLoginIsFuncionario(Session session) {
		if (session.getAttribute("status") == null) {
			return false;
		} else {
			String status = (String) session.getAttribute("status");
			if (status.equalsIgnoreCase("offline"))
				return false;
			if (status.equalsIgnoreCase("onlineFuncionario"))
				return true;
			else
				return false;
		}
	}

	private void MostrarMensagem(String msn) {
		if (msn.equalsIgnoreCase("1")) {
			msgbox.mensagemSucesso("Usuário logado com sucesso....");
		}
		if (msn.equalsIgnoreCase("2")) {
			msgbox.mensagemError("Área restrita, Por gentileza logar no sistema...");
		}
		if (msn.equalsIgnoreCase("3")) {
			msgbox.mensagemError("Por gentileza logar no sistema...");
		}
	}

	public Window getWindowDesativar() {
		return windowDesativar;
	}

	public void setWindowDesativar(Window windowDesativar) {
		this.windowDesativar = windowDesativar;
	}

	public M getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(M selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public boolean isDisplayEdit() {
		return displayEdit;
	}

	public void setEntity(M entity) {
		this.entity = entity;
	}

	public List<C> getLstEntities() {
		return lstEntities;
	}

	public void setLstEntities(List<C> lstEntities) {
		this.lstEntities = lstEntities;
	}

	public C getController() {
		return controller;
	}

	public void setController(C controller) {
		this.controller = controller;
	}
	
	public M getEntity() {
		return entity;
	}
	
	 private void logout() {
		 Session session;
		 session = Executions.getCurrent().getSession();
		 session.removeAttribute("status");
		 session.removeAttribute("idaluno");
		 session.removeAttribute("aluno.idusuario");
		 session.removeAttribute("idusuario");
		 Executions.sendRedirect("/index.zul");
	}
	public String getRecordMode() {
		return recordMode;
	}
	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}
	public Window getWin() {
		return win;
	}
	public void setWin(Window win) {
		this.win = win;
	}
	public Integer getCurSelectedEntity() {
		return curSelectedEntity;
	}
	public void setCurSelectedEntity(Integer curSelectedEntity) {
		this.curSelectedEntity = curSelectedEntity;
	}

}