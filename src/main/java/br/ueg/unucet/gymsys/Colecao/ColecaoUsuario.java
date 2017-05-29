package br.ueg.unucet.gymsys.Colecao;

import java.util.ArrayList;
import java.util.HashMap;


import br.ueg.unucet.gymsys.Model.Usuario;


/*
 * Classe responsável por transormar a coleção de dados recebidos do banco de dados.
 * Em uma coleçao do tipo List de obejtos de uma entidade do tipo Model...
 * 
 */

public class ColecaoUsuario {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public  ArrayList<Usuario> getAll() {
        return usuarios;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){

			for (HashMap<String, Object> hashMap : result) {
				Usuario usuario = new Usuario(	Integer.parseInt((String) hashMap.get("idusuario")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("senha"),
												Integer.parseInt((String) hashMap.get("tipousuario")),
												(hashMap.get("ativo").equals("t")));
				usuarios.add(usuario);
			}
				
		}
    }

	public br.ueg.unucet.gymsys.Model.Usuario getIndice(int indice) {
		if(usuarios.isEmpty()) {
			return null;
		}
		   return usuarios.get(indice);
	}
}
