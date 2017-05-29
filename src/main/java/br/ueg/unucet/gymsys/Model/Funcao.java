package br.ueg.unucet.gymsys.Model;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;

@Table(nome = "funcao")
public class Funcao extends Model<Integer> {

	public Funcao() {
	}

	@Campo(nome = "idfuncao", pk = true)
	private int idfuncao;

	@Campo(nome = "nome", obrigatorio = true)
	private String nome;

	@Campo(nome = "descricao", obrigatorio = true)
	private String descricao;

	@Campo(nome = "cor", obrigatorio = true)
	private String cor;

	@Campo(nome = "ativo", obrigatorio = true)
	private boolean ativo;

	public Funcao(int idfuncao, String nome, String descricao, String cor, boolean ativo) {
		super();
		this.idfuncao = idfuncao;
		this.nome = nome;
		this.descricao = descricao;
		this.cor = cor;
		this.ativo = ativo;
	}

	@Override
	public boolean getAtivo() {
		return ativo;
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}

	public int getIdfuncao() {
		return idfuncao;
	}

	public void setIdfuncao(int idfuncao) {
		this.idfuncao = idfuncao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
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

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCriterio() {
		return "nome";
	}

	public String getOrdenacao() {
		return "nome";
	}

}
