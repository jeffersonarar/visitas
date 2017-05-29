package br.ueg.unucet.gymsys.View.zk_calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.calendar.api.CalendarEvent;

import br.ueg.unucet.gymsys.Controller.VisitaController;
import br.ueg.unucet.gymsys.Model.Visita;
import br.ueg.unucet.gymsys.Util.Response;

public class DemoCalendarData extends VisitaController {

	private List<CalendarEvent> calendarEvents = new LinkedList<CalendarEvent>();
	private final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private Calendar cal = Calendar.getInstance();
	private List<Visita> listAgendamento;

	@SuppressWarnings("unchecked")
	public Response listar() {
		String keyword = " ";
		setListAgendamento((List<Visita>) getLstEntities((keyword)));
		return null;
	}

	public DemoCalendarData() {
		init();
	}



	public void popularCalendario() {
		int mod = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String date2 = mod > 9 ? year + "/" + mod + "" : year + "/" + "0" + mod;
		String date1 = --mod > 9 ? year + "/" + mod + "" : year + "/" + "0" + mod;
		++mod;
		String date3 = ++mod > 9 ? year + "/" + mod + "" : year + "/" + "0" + mod;
		for (Visita agendamento : listAgendamento) {
			SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy/MM");
			String mesAno = DATA_FORMAT.format(agendamento.getData_inicio());
			SimpleDateFormat DATA_FORMAT1 = new SimpleDateFormat("dd");
			String dia = DATA_FORMAT1.format(agendamento.getData_inicio());
			calendarEvents.add(new DemoCalendarEvent(getDate(mesAno + "/" + dia + " 00:00"),
					getDate(mesAno + "/" + dia + " 23:59"), agendamento.getFuncao().getCor(),
					agendamento.getFuncao().getCor(), texto(agendamento)));
		}
	}

	private void init() {
		this.listar();
		popularCalendario();
	}

	public String texto(Visita r) {
		return "Visita  " +r.getHora_inicio() +" "+ r.getAgendadopor()
				+ ".";

	}

	private Date getDate(String dateText) {
		try {
			return DATA_FORMAT.parse(dateText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CalendarEvent> getCalendarEvents() {
		return calendarEvents;
	}

	public List<Visita> getListAgendamento() {
		return listAgendamento;
	}

	public void setListAgendamento(List<Visita> listAgendamento) {
		this.listAgendamento = listAgendamento;
	}

}
