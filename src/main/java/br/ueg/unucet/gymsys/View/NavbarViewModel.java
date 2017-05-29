package br.ueg.unucet.gymsys.View;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;

import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Response;

@SuppressWarnings("serial")
public class NavbarViewModel extends Div {

	@Wire
	A atask, anoti, amsg;

	private List<?> lstUsuarios;

	@Init
	public void init() {

	}

	public Response populaDadosSolicitacoes() {
		Response ret = new Response(true);
	
		return ret;
	}

	public Response populaDadosUsuario() {
		Response ret = new Response(true);
	


		return ret;
	}

	@Command
	public Response redireciona() {
		Response ret = new Response(true);
	
		return ret;
	}

	@Listen("onOpen = #taskpp")
	public void toggleTaskPopup(OpenEvent event) {
		toggleOpenClass(event.isOpen(), atask);
	}

	@Listen("onOpen = #notipp")
	public void toggleNotiPopup(OpenEvent event) {
		toggleOpenClass(event.isOpen(), anoti);
	}

	@Listen("onOpen = #msgpp")
	public void toggleMsgPopup(OpenEvent event) {
		toggleOpenClass(event.isOpen(), amsg);
	}

	@Command
	public void doLogout() {
	//	authService.logout();
		Executions.sendRedirect("/login.zul");
	}

	// Toggle open class to the component
	public void toggleOpenClass(Boolean open, Component component) {
		HtmlBasedComponent comp = (HtmlBasedComponent) component;
		String scls = comp.getSclass();
		if (open) {
			comp.setSclass(scls + " open");
		} else {
			comp.setSclass(scls.replace(" open", ""));
		}
	}


	public List<?> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<?> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

}
