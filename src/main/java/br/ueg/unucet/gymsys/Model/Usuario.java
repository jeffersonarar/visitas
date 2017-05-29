package br.ueg.unucet.gymsys.Model;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Util.Criptografia;

@Table(nome="usuario")
public class Usuario extends Model<Integer>{
	
	@Campo(nome="idusuario", pk=true)
	private int idusuario;
	
	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="senha",obrigatorio=true)
	private String senha;
	
	@Campo(nome="tipousuario",obrigatorio=true)
	private int tipousuario;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public Usuario(){}
	
	public Usuario(Object object,  String nome, String senha, Object object2, boolean ativo){
		this.idusuario = (Integer) object;
		this.nome = nome;
		this.senha = senha;
		this.tipousuario = (Integer) object2;
		this.ativo = ativo;
	}
	
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(int tipousuario) {
		this.tipousuario = tipousuario;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getTabelaNome() {
		return "usuario";
	}
	public String getVariaveisI() {
		return "nome, senha, tipousuario, ativo ";
	}
	public String getatributosI() {
		String atributos = "'"+ this.nome+ "',";
		atributos = atributos + "'"+ Criptografia.criptografar(this.senha) + "',";
		atributos = atributos + " "+ this.tipousuario + " ,";
		if(this.ativo){
			atributos = atributos + " "+ "true" + " ";
		} else{
			atributos = atributos + " "+ "false" + " ";
		}
		return atributos;
	}
	
	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}
	
	public int getId() {
		return this.idusuario;
	}
	public void setId(int id) {
		this.idusuario = id;
	}

	@Override
	public String getCriterio() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOrdenacao() {
		return "nome";
	}
	
}

