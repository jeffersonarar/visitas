package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoVisita;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Visita;
import br.ueg.unucet.gymsys.Util.Response;
import br.ueg.unucet.gymsys.Util.Utils;


public class VisitaController extends GenericController<Visita>{

	private Visita reserva = new Visita();
	private Visita visitaSelecionado;
	Utils utils = new Utils();
	
	public List<?> getLstEntities(String keyword) {
		ColecaoVisita listareserva = new ColecaoVisita();
		try {
			if (visitaSelecionado == null) {
				listareserva.setAll(dao.pesquisarNome(reserva, keyword));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listareserva.getAll();
	}
	
	

	
	
	
	public List<?> getLstEntities(Boolean keyword) {
		ColecaoVisita listareserva = new ColecaoVisita();
		try {
			if (visitaSelecionado == null) {
				listareserva.setAll(dao.pesquisarVisitas(reserva, keyword));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listareserva.getAll();
	}
	
	public List<?> getLstCriteria(String keyword) {
		ColecaoVisita listareserva = new ColecaoVisita();
		try {
			listareserva.setAll(dao.pesquisarCriterio(reserva, Integer.parseInt(keyword)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listareserva.getAll();
	}
	
	public Visita getEntity(String id) {
		Visita reserva = new Visita();
		reserva.setIdvisita(Integer.parseInt(id));
		ColecaoVisita listaReserva = new ColecaoVisita();
		try {
			listaReserva.setAll(dao.pesquisarID(reserva));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reserva = ((ColecaoVisita) listaReserva).getIndice(0);
		return reserva;
	}
	
	@Override
	public Response validar(IModel<?> imodel) {
		Response res = new Response(true,"", null);
		setReserva((Visita) imodel);
		Date dataAtual = new Date();
		
	/*	if(getReserva().getData_inicio() == null){
			return  new Response(false, "Campo data inicio é obrigatório!", TypeMessage.AVISO);
		}
		if(getReserva().getData_inicio().before(dataAtual)){
			return  new Response(false, "Data inicio deve ser maior que a data atual.", TypeMessage.AVISO);
		}
		if(Integer.valueOf(getReserva().getHora_inicio().trim().substring(0, 2)) > Integer.valueOf(getReserva().getHora_fim().trim().substring(0, 2))){
			return res = new Response(false, "Hora inicio deve ser inferior que a hora final!", TypeMessage.AVISO);
		}
		if(getReserva().getFuncao() == null){
			return  new Response(false, "Campo funcao é obrigatório!", TypeMessage.AVISO);
		}
		if(getReserva().getEmail() == null){
			return  new Response(false, "Campo email é obrigatório!", TypeMessage.AVISO);
		}
		if(utils.isEmail(getReserva().getEmail()) == false){
			return  new Response(false, "Email é invalido!", TypeMessage.AVISO);
		}
		*/
		return res;
	}

	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Visita getReserva() {
		return reserva;
	}

	public void setReserva(Visita reserva) {
		this.reserva = reserva;
	}

	public Visita getvisitaSelecionado() {
		return visitaSelecionado;
	}

	public void setvisitaSelecionado(Visita visitaSelecionado) {
		this.visitaSelecionado = visitaSelecionado;
	}
	
	

}
