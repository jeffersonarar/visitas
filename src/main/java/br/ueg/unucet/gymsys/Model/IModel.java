package br.ueg.unucet.gymsys.Model;

public interface IModel<TipoPK> {
	
	public String getPKName();
	
	public String getPkFieldName();
	
	public TipoPK getPK();
	
	public void setPK(TipoPK pk);
	
	public String getTableName();
	
	public String getTableColumnNames();
	
	public void resetFields();
	
	public String getVariaveisPesquisarNome();
	
	public String getCriterio();
	
	public String getOrdenacao();
	
}
