package br.ueg.unucet.gymsys.Model;

import java.io.Serializable;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;


@Table(nome="municipio")
public class Municipio extends Model<Integer> implements Serializable,Cloneable{

	@Campo(nome="idmunicipio", pk=true)
	private int idmunicipio;
	
	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome="idestado",obrigatorio=true)
	private Estado estado;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;

	public Municipio() {
	}

	public Municipio(Integer idmunicipio, String nome, String descricao, Estado estado, boolean ativo) {
		super();
		this.idmunicipio = idmunicipio;
		this.nome = nome;
		this.descricao = descricao;
		this.estado = estado;
		this.ativo = ativo;
	}







	public Municipio(Integer idmunicipio, String nome, String descricao, boolean ativo) {
		super();
		this.idmunicipio = idmunicipio;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}







	public void setIdmunicipio(Integer idmunicipio) {
		this.idmunicipio = idmunicipio;
	}



	public String getOrdenacao() {
		return "nome";
	}

	public String getVariaveisPesquisarNome() {
		return "nome";
	}
	
	
	public int getIdmunicipio() {
		return idmunicipio;
	}

	public void setIdmunicipio(int idmunicipio) {
		this.idmunicipio = idmunicipio;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}

