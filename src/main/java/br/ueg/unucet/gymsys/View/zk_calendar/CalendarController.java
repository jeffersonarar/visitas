package br.ueg.unucet.gymsys.View.zk_calendar;

import java.util.Calendar;
import java.util.TimeZone;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Util.ParametrosWindow;
import br.ueg.unucet.gymsys.View.VisitaViewModel;

public class CalendarController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;


	
	ParametrosWindow paramWin = new ParametrosWindow();
	
	DemoCalendarData demoC = new DemoCalendarData();

	@Wire
	private Window win;
	
	@Wire
	private Include inc;



	@Wire
	private Calendars calendars;
	@Wire
	private Textbox filter;

	@Wire
	private Div calendar;

	private DemoCalendarModel calendarModel;


	// the in editing calendar ui event
	private CalendarsEvent calendarsEvent = null;


	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	//	getLocalList();
		calendarModel = new DemoCalendarModel(new DemoCalendarData().getCalendarEvents());
		calendars.setModel(this.calendarModel);
	

	}
	
	
	@Listen("onClick = #btPesquisar")
	public void pesquisar() {
		
		/*	if(localCombobox.getSelectedItem() == null){
		
		}else{
			setLocalSelecionado((Local) localCombobox.getSelectedItem().getValue());
			demoC.pesquisar(localSelecionado);
			calendarModel = new DemoCalendarModel(demoC.getCalendarEvents());
			calendars.setModel(this.calendarModel);
		}*/
	}

	@Listen("onClick = #btPesquisarTodos")
	public void pesquisarTodos() {
		calendarModel = new DemoCalendarModel(new DemoCalendarData().getCalendarEvents());
		calendars.setModel(this.calendarModel);
	}
	
	// control the calendar position
	@Listen("onClick = #today")
	public void gotoToday() {
		TimeZone timeZone = calendars.getDefaultTimeZone();
		calendars.setCurrentDate(Calendar.getInstance(timeZone).getTime());
	}

	@Listen("onClick = #btReserva")
	public void abrirReserva() {
		Executions.sendRedirect("/paginas/visita/cadastrar.zul");
	}

	
	


	
	@Listen("onClick = #next")
	public void gotoNext() {
		calendars.nextPage();
	}

	@Listen("onClick = #prev")
	public void gotoPrev() {
		calendars.previousPage();
	}

	// control page display
	@Listen("onClick = #pageDay")
	public void changeToDay() {
		calendars.setMold("default");
		calendars.setDays(1);
	}

	@Listen("onClick = #pageWeek")
	public void changeToWeek() {
		calendars.setMold("default");
		calendars.setDays(7);
	}

	@Listen("onClick = #pageMonth")
	public void changeToYear() {
		calendars.setMold("month");
	}

	

	// listen to the calendar-create and edit of a event data
	@Listen("onEventCreate = #calendars; onEventEdit = #calendars")
	public void createEvent(CalendarsEvent event) {
		calendarsEvent = event;
		
		paramWin.setData(calendarsEvent.getEndDate());
		VisitaViewModel rvm = new VisitaViewModel();
		rvm.abrirWindow(paramWin);
		// to display a shadow when editing
		
	}

	// listen to the calendar-update of event data, usually send when user drag
	// the event data
	@Listen("onEventUpdate = #calendars")
	public void updateEvent(CalendarsEvent event) {
		DemoCalendarEvent data = (DemoCalendarEvent) event.getCalendarEvent();
		data.setBeginDate(event.getBeginDate());
		data.setEndDate(event.getEndDate());
		calendarModel.update(data);
	}

	// listen to queue message from other controller
	@Subscribe(value = QueueUtil.QUEUE_NAME)
	public void handleQueueMessage(Event event) {
		if (!(event instanceof QueueMessage)) {
			return;
		}
		QueueMessage message = (QueueMessage) event;
		switch (message.getType()) {
		case DELETE:
			calendarModel.remove((DemoCalendarEvent) message.getData());
			// clear the shadow of the event after editing
			calendarsEvent.clearGhost();
			calendarsEvent = null;
			break;
		case OK:
			if (calendarModel.indexOf((DemoCalendarEvent) message.getData()) >= 0) {
				calendarModel.update((DemoCalendarEvent) message.getData());
			} else {
				calendarModel.add((DemoCalendarEvent) message.getData());
			}
		case CANCEL:
			// clear the shadow of the event after editing
			calendarsEvent.clearGhost();
			calendarsEvent = null;
			break;
		}
	}


}
