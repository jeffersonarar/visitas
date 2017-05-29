package br.ueg.unucet.gymsys.Colecao;


import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.EstadoController;
import br.ueg.unucet.gymsys.Model.Estado;
import br.ueg.unucet.gymsys.Model.Municipio;


public class ColecaoMunicipio {
	private ArrayList<Municipio> listaMunicipios = new ArrayList<Municipio>();
	
	public  ArrayList<Municipio> getAll() {
        return listaMunicipios;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		EstadoController EstadoController = new EstadoController();
		Estado estado = null;
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				estado = EstadoController.getEntity((String) hashMap.get("idestado"));
				Municipio municipio = new Municipio(	Integer.parseInt((String) hashMap.get("idmunicipio")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("descricao"),
												estado,
												(hashMap.get("ativo").equals("t")));
				listaMunicipios.add(municipio);
			}
		}
    }

	
	public Municipio getIndice(int indice) {
		if(listaMunicipios.isEmpty()){
			return null;
		}
		   return listaMunicipios.get(indice);
	}
}