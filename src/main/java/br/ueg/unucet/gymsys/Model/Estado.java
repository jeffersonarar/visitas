package br.ueg.unucet.gymsys.Model;

import java.io.Serializable;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;


@Table(nome="estado")
public class Estado extends Model<Integer> implements Serializable,Cloneable{

	@Campo(nome="idestado", pk=true)
	private int idestado;
	
	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome="sigla",obrigatorio=true)
	private String sigla;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;

	public Estado() {
	}



	public Estado(Integer idestado, String nome, String descricao, String sigla, boolean ativo) {
		super();
		this.idestado = idestado;
		this.nome = nome;
		this.descricao = descricao;
		this.sigla = sigla;
		this.ativo = ativo;
	}


	public String getOrdenacao() {
		return "nome";
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}





	public Integer getIdestado() {
		return idestado;
	}



	public void setIdestado(Integer idestado) {
		this.idestado = idestado;
	}



	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
}

