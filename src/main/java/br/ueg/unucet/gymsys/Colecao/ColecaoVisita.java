package br.ueg.unucet.gymsys.Colecao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.FuncaoController;
import br.ueg.unucet.gymsys.Controller.MunicipioController;
import br.ueg.unucet.gymsys.Controller.VisitaController;
import br.ueg.unucet.gymsys.Model.Funcao;
import br.ueg.unucet.gymsys.Model.Municipio;
import br.ueg.unucet.gymsys.Model.Visita;

public class ColecaoVisita {
	private ArrayList<Visita> listareservas = new ArrayList<Visita>();
	Funcao funcao = new Funcao();
	Municipio municipio = new Municipio();

	
	Date data;
	Date date, dataIni, dataFim, dataCad;

	public ArrayList<Visita> getAll() {
		return listareservas;
	}

	public void setAll(ArrayList<HashMap<String, Object>> result) {
		VisitaController reservaController = new VisitaController();
		Date dataVisita = null;
		if (result != null) {
			for (HashMap<String, Object> hashMap : result) {
				dataVisita = null;

			
				if (hashMap.get("data_inicio") != null) {

					try {

						DateFormat out = new SimpleDateFormat("yyyy-MM-dd");
						dataIni = out.parse((String) hashMap.get("data_inicio").toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				if (hashMap.get("data_cadastro") != null) {

					try {

						DateFormat out = new SimpleDateFormat("yyyy-MM-dd");
						dataCad = out.parse((String) hashMap.get("data_cadastro").toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (hashMap.get("data_fim") != null) {

					try {

						DateFormat out = new SimpleDateFormat("yyyy-MM-dd");
						dataFim = out.parse((String) hashMap.get("data_fim").toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if(hashMap.get("idfuncao")!=  null){
					FuncaoController cPosto= new FuncaoController();
					funcao = cPosto.getEntity((String)hashMap.get("idfuncao"));
				}
				if(hashMap.get("idmunicipio")!=  null){
					MunicipioController cMunicipio= new MunicipioController();
					municipio = cMunicipio.getEntity((String)hashMap.get("idmunicipio"));
				}
			
		
				
				Visita reserva = new Visita(Integer.parseInt((String) hashMap.get("idvisita")),
											  funcao,
											  (String) hashMap.get("agendadopor"),
											  (String) hashMap.get("empresa"),
											  (String) hashMap.get("tefefone"),
											  (String) hashMap.get("celular"),
											  (String) hashMap.get("endereco"),
											  (String) hashMap.get("quadra"),
											  (String) hashMap.get("lote"),
											  (String) hashMap.get("email"),
											  (String) hashMap.get("contato"),
											  dataIni,
											  dataFim,
											  dataCad,
											  (String) hashMap.get("hora_inicio"),
											  (String) hashMap.get("hora_fim"),
											  (hashMap.get("ativo").equals("t")),
											  (hashMap.get("confirmado").equals("t")),
											  Integer.parseInt((String) hashMap.get("numero")),
											  (String) hashMap.get("bairro"),
											  municipio,
											  (String) hashMap.get("motivo")
										);
				listareservas.add(reserva);
			}
		}
	}

	public br.ueg.unucet.gymsys.Model.Visita getIndice(int indice) {
		if (listareservas.isEmpty()) {
			return null;
		}
		return listareservas.get(indice);
	}
}
