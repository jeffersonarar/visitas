package br.ueg.unucet.gymsys.Model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.ueg.unucet.gymsys.Util.Reflection;
import br.ueg.unucet.gymsys.Anotations.Campo;

public abstract class Model<TipoPK> implements IModel<TipoPK>{

	private boolean ativo;
	
	public String getPKName(){
		return Reflection.getPkName(this);
	}
	
	public String getPkFieldName(){
		return Reflection.getPkFieldName(this);
	}
	
	@SuppressWarnings("unchecked")
	public TipoPK getPK(){
		return (TipoPK)Reflection.getPkValue(this);
	}
	
	public void setPK(TipoPK pk){
		Reflection.setPkValue(this, pk);
	}
	
	public String getTableName(){
		return Reflection.getTableName(this);
	}
	/** 
	 * @return retorna o nome das colunas da tabela separada por virgula
	 */
	public String getTableColumnNames(){
		String retorno = "";
		Class<?> cls = this.getClass();
		Field fld[] = cls.getDeclaredFields();
		
		for(int i=0; i<fld.length; i++){
			Campo campo = fld[i].getAnnotation(Campo.class);
			if(campo !=null){
				retorno = retorno +","+campo.nome();
			}
		}
		 retorno = retorno.substring(1);		
		return retorno;
	}
	
	/**
	 * limpa todos os atributos da entidade
	 */
	public void resetFields(){
		Class<?> cls = this.getClass();
		Method mtd[] = cls.getDeclaredMethods();
		
		for(int i=0; i<mtd.length; i++){
			if(mtd[i].getName().substring(0, 3).equalsIgnoreCase("set") &&
			   mtd[i].getParameterTypes().length==1){
				try {
					mtd[i].invoke(this, new Object[]{ null });
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
	
	public abstract String getVariaveisPesquisarNome();
	


	public String getCriterio() {
		return null;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
