package br.ueg.unucet.gymsys.DAO;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Connection.Connect;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Estado;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Message;
import br.ueg.unucet.gymsys.Util.Reflection;
import br.ueg.unucet.gymsys.Util.Response;


public class GenericDAO {

	static GenericDAO genericDAO;
	Message message = new Message();
	
	static { genericDAO = null; }
		
	public static GenericDAO obterInstancia(){
			if (genericDAO == null ) {
				genericDAO = new GenericDAO();
			}
			return genericDAO;
		}
	
	public Response inserir(IModel<?> entidade){
		Response res = null;
		
		String auxFieldsName="";
		String auxFieldsValues="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 1; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						String str = "";
						if(Reflection.getFieldValue(entidade, fld[i].getName()) instanceof IModel<?>){
							str = "" + (((IModel<?>) Reflection.getFieldValue(entidade, fld[i].getName())).getPK());
						}else{
							str = "" + Reflection.getFieldValue(entidade, fld[i].getName());
						}
						auxFieldsName=auxFieldsName+","+cmp.nome();
						auxFieldsValues=auxFieldsValues+", '"+ str +"'";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		
		String sql = "insert into " + entidade.getTableName();
			   sql = sql + "(" + auxFieldsName.substring(1) + ")";			   
			   sql = sql + " values(" + auxFieldsValues.substring(1) + ")";
		
		
		if(Connect.getConexao()){
			int i = Connect.runSQL(sql);
			System.out.println(sql);
			if (i == 1){
				Connect.close();
				return new Response(true,"O "+ entidade.getTableName()+ "foi inserido com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O " + entidade.getTableName() +"  Não Foi Inserido problema no DAO.inserir");  
				Connect.close();
				return  new Response(false, "Não Foi Inserido", TypeMessage.ERROR);
			}
				
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi Inserido problema no Connect.getConexão()");
		
		return  new Response(false,"Não Foi Inserido problema no Connect.getConexão()", TypeMessage.ERROR);
	}
	
	
	public Response alterar(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						String str = "";
						if(Reflection.getFieldValue(entidade, fld[i].getName()) instanceof IModel<?>){
							str = "" + (((IModel<?>) Reflection.getFieldValue(entidade, fld[i].getName())).getPK());
						}else{
							str = "" + Reflection.getFieldValue(entidade, fld[i].getName());
						}
						auxFields=auxFields+","+cmp.nome()+"= '" + str + "'";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set "+auxFields.substring(1) + " ";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		
		if(Connect.getConexao()){
			System.out.println("sql = "+sql);
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				return new Response(true, entidade.getTableName()+ " Alterado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O " + entidade.getTableName() + "Não Foi Alterado problema no DAO.Alterar");  
				Connect.close();
				return  new Response(false, "Não Foi Alterado", TypeMessage.ERROR);
			}
		}
		System.out.println("O "+ entidade.getTableName() +" Não Foi Alterado problema no DAO.alterar.Connect.getConexão");
		return new Response(false);
	}
	
	public Response excluir(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						auxFields=auxFields+","+cmp.nome()+"=?";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set ativo= 'false'";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		System.out.println("sql = "+sql);
		if(Connect.getConexao()){
			
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				return new Response(false, entidade.getTableName()+ " Desativado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O "+ entidade.getTableName() + "Não Foi excluido problema no DAO.excluir");  
				Connect.close();
				return new Response(false);
			}
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi Excluido problema no DAOUsuario.excluir.Connect.getConexão");
		return new Response(false);
	}

	
	public Response confirmar(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						auxFields=auxFields+","+cmp.nome()+"=?";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set confirmado = 'true'";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		System.out.println("sql = "+sql);
		if(Connect.getConexao()){
			
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				
				return new Response(false, entidade.getTableName()+ " Confirmado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O "+ entidade.getTableName() + "Não foi confirmado problema no DAO.confirmar");  
				
				Connect.close();
				return new Response(false);
			}
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi confirmado problema no DAOReserva.excluir.Connect.getConexão");
		return new Response(false);
	}
	
	
	
	public Response ativar(IModel<?> entidade){
		String auxFields="";
		Class<?> cls = entidade.getClass();	
		Field[] fld = cls.getDeclaredFields();
		
		try {
			for(int i = 0; i < fld.length; i++){
				Campo cmp = fld[i].getAnnotation(Campo.class);				
				if(cmp!=null){
					if(Reflection.getFieldValue(entidade, fld[i].getName())!=null){
						auxFields=auxFields+","+cmp.nome()+"=?";
					}
				}				
			}
		}catch (Throwable e) {
			System.err.println(e);
			
		}		
		
		String sql = "update " + entidade.getTableName() + " " ;		
		   sql = sql + "set ativo= 'true'";
		   sql = sql + " where " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		System.out.println("sql = "+sql);
		if(Connect.getConexao()){
			
			int i = Connect.runSQL(sql);
			if (i == 1){
				Connect.close();
				return new Response(false, entidade.getTableName()+ " Ativado com sucesso!", TypeMessage.SUCESSO);
				
			}else{
				System.out.println("O "+ entidade.getTableName() + "Não Foi excluido problema no DAO.excluir");  
				Connect.close();
				return new Response(false);
			}
		}
		System.out.println("O " + entidade.getTableName() + "Não Foi Excluido problema no DAOUsuario.excluir.Connect.getConexão");
		return new Response(false);
	}
	/*
	 * função pesquisarUsuario pode ser chamada por uma string onde pesquisa qualquer 
	 * parte da palavra do titulo, diretor ou genero
	 * ou por um inteiro onde se pesquisa pelo id do filme..
	 */

	
	public ArrayList<HashMap<String,Object>>  pesquisarNome(IModel<?> entidade, String nome) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = true or ";
		
		String nomeVariaveis = entidade.getVariaveisPesquisarNome();
		String[] vetorVariaveis = new String [nomeVariaveis.split(",").length];
		vetorVariaveis = nomeVariaveis.split(",");
		for (int i = 0; i < vetorVariaveis.length ; i++){
			sql = sql + " "+ vetorVariaveis[i] + " LIKE '%" + nome + "%' OR";
		}
		
		sql = sql.substring(0, sql.length()-2);
		sql = sql + " ;";
		
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
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
	public ArrayList<HashMap<String,Object>> listarTodos(IModel<?> entidade) throws SQLException{
		String sql = null;
		
			String sqlAll = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
			sqlAll = sqlAll + " where ativo = true";
			sql = sqlAll;
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
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
	public ArrayList<HashMap<String,Object>> listarMunicpio(Estado estado) throws SQLException{
		String sql = null;
		
		if(estado != null){
			
		
			String sqlAll = "select * from municipio  where ativo = true and"
					+ " idestado = "+ estado.getIdestado()+ ";";
			sql = sqlAll;
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
		}
	return null;
	}
	public ArrayList<HashMap<String,Object>>  pesquisarVisitas(IModel<?> entidade, Boolean confirmado) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = true and confirmado = " + confirmado;
		
	
		sql = sql + " ;";
		
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
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
			
public ArrayList<HashMap<String,Object>>  pesquisarNomeAtivo(IModel<?> entidade, String nome) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = 'true' and ";
		
		String nomeVariaveis = entidade.getVariaveisPesquisarNome();
		String[] vetorVariaveis = new String [nomeVariaveis.split(",").length];
		vetorVariaveis = nomeVariaveis.split(",");
		for (int i = 0; i < vetorVariaveis.length ; i++){
			sql = sql + " "+ vetorVariaveis[i] + " LIKE '%" + nome + "%' OR";
		}
		
		sql = sql.substring(0, sql.length()-2);
		sql = sql + " ;";
		
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
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
	public ArrayList<HashMap<String,Object>>  pesquisarID(IModel<?> entidade) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where  ativo = 'true' and " + entidade.getPKName() + "='" + String.valueOf(entidade.getPK()) + "'";
		
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
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}
	
	
public ArrayList<HashMap<String,Object>>  pesquisarCriterio(IModel<?> entidade, int criterio) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = 'true' and " + entidade.getCriterio() +" = " +criterio+ " order by " + entidade.getOrdenacao() + " ;";
		
		
		System.out.println("sql pesquisarCriterio :"+sql);
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
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}


public Response validarItemUnico(IModel<?> entidade, String[] valores, String[]nomesVariaveis) throws SQLException{
	int contador = 0;
	String sql = null;
	if(valores.length != nomesVariaveis.length){
		return  new Response(false, "Validacão de item ùnico Incorreta, valores devem possuir o mesmo número de variáveis", TypeMessage.ERROR);
	}
	if(Connect.getConexao()){
		while (contador < valores.length){
			sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
			sql = sql + " where "+ nomesVariaveis[contador]+ " = '"+ valores[contador]+"' ;";
			ResultSet rs =  Connect.setResultSet(sql);
			if(rs.next()){
				return  new Response(false, "Já possui este registro no campo " + nomesVariaveis[contador] , TypeMessage.ERROR);
			}
			contador++;
		}
		return  new Response(true, "", TypeMessage.SUCESSO);
	}else{
		return  new Response(false, "Erro de conexão com o banco na validação de item único...", TypeMessage.ERROR);
	}
}


}


