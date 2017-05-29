package br.ueg.unucet.gymsys.Colecao;

import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Model.Estado;


public class ColecaoEstado {

	
private ArrayList<Estado> listaEstados = new ArrayList<Estado>();
	
	public  ArrayList<Estado> getAll() {
        return listaEstados;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				Estado Estado = new Estado(	
												Integer.parseInt((String) hashMap.get("idestado")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("descricao"),
												(String) hashMap.get("sigla"),
												(hashMap.get("ativo").equals("t")));
				
				listaEstados.add(Estado);
			}
		}
    }

	public Estado getIndice(int indice) {
		if(listaEstados.isEmpty()){
			return null;
		}
		   return listaEstados.get(indice);
	}
}
