package br.ueg.unucet.gymsys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Connection.Connect;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Usuario;

public class UsuarioDAO {
	
	public ArrayList<HashMap<String,Object>>  logar(String nome, String senha) throws SQLException{
		Usuario usuario = new Usuario();
	
		String sql = "select "+ usuario.getTableColumnNames()+" from " + usuario.getTableName() + " ";
		sql = sql + " where nome " + "='" + nome + "' and senha = '" + senha + "'";
		
		System.out.println("sql:"+sql);
		
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		return null;
	}
	
	public ArrayList<HashMap<String,Object>>  pesquisarNomeUsuario(String nome) throws SQLException{
		Usuario usuario = new Usuario();
	
		String sql = "select "+ usuario.getTableColumnNames()+" from " + usuario.getTableName() + " ";
		sql = sql + " where nome " + "='" + nome + "';";
		
		System.out.println("sql:"+sql);
		
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		return null;
	}

}
