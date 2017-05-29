package br.ueg.unucet.gymsys.Colecao;

import java.util.ArrayList;
import java.util.HashMap;


import br.ueg.unucet.gymsys.Model.Funcao;;

public class ColecaoFuncao {
	private ArrayList<Funcao> listaPostos = new ArrayList<Funcao>();
	
	public  ArrayList<Funcao> getAll() {
        return listaPostos;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){
			
			for (HashMap<String, Object> hashMap : result) {
	
				Funcao posto = new Funcao(	Integer.parseInt((String) hashMap.get("idfuncao")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("descricao"),
												(String) hashMap.get("cor"),
												(hashMap.get("ativo").equals("t")));
				listaPostos.add(posto);
			}
				
		}
    }

	public br.ueg.unucet.gymsys.Model.Funcao getIndice(int indice) {
		   return listaPostos.get(indice);
	}
}