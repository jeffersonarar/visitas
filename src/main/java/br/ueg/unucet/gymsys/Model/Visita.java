package br.ueg.unucet.gymsys.Model;

import java.util.Date;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;

@Table(nome="visita")
public class Visita extends Model<Integer>{

	public Visita() {
	}



	@Campo(nome="idvisita", pk=true)
	private int idvisita;
	
	
	@Campo(nome="idfuncao",obrigatorio=true)
	private Funcao funcao;
	
	
	@Campo(nome="idmunicipio",obrigatorio=true)
	private Municipio municipio;
	
	@Campo(nome="agendadopor",obrigatorio=true)
	private String agendadopor;
	
	
	@Campo(nome="empresa",obrigatorio=true)
	private String empresa;
	
	@Campo(nome="telefone",obrigatorio=true)
	private String telefone;
	
	@Campo(nome="celular",obrigatorio=true)
	private String celular;
	
	@Campo(nome="endereco",obrigatorio=true)
	private String endereco;
	
	@Campo(nome="quadra",obrigatorio=true)
	private String quadra;
	
	@Campo(nome="lote",obrigatorio=true)
	private String lote;

	@Campo(nome="email",obrigatorio=true)
	private String email;
	
	@Campo(nome="contato",obrigatorio=true)
	private String contato;
	

	@Campo(nome="data_inicio")
	private Date data_inicio;
	
	@Campo(nome="data_fim")
	private Date data_fim;
	
	@Campo(nome="data_cadastro")
	private Date data_cadastro;
	
	@Campo(nome="hora_inicio")
	private String hora_inicio;
	
	@Campo(nome="hora_fim")
	private String hora_fim;

	@Campo(nome="ativo",obrigatorio=false)
	private boolean ativo;
	
	@Campo(nome="confirmado",obrigatorio=false)
	private boolean confirmado;
	
	@Campo(nome="numero",obrigatorio=false)
	private Integer numero;
	
	@Campo(nome="bairro",obrigatorio=false)
	private String bairro;
	
	@Campo(nome="motivo",obrigatorio=false)
	private String motivo;

	public Visita(int idvisita, Funcao funcao, String agendadopor,  String empresa, String tefefone,
			String celular, String endereco, String quadra, String lote, String email, String contato, Date data_inicio,
			Date data_fim, Date data_cadastro, String hora_inicio, String hora_fim, boolean ativo, boolean confirmado,
			Integer numero, String bairro, Municipio municipio, String motivo) {
		super();
		this.idvisita = idvisita;
		this.funcao = funcao;
		this.agendadopor = agendadopor;
		this.empresa = empresa;
		this.telefone = tefefone;
		this.celular = celular;
		this.endereco = endereco;
		this.quadra = quadra;
		this.lote = lote;
		this.email = email;
		this.contato = contato;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.data_cadastro = data_cadastro;
		this.hora_inicio = hora_inicio;
		this.hora_fim = hora_fim;
		this.ativo = ativo;
		this.confirmado = confirmado;
		this.numero = numero;
	}

	public String getVariaveisI() {
		return "funcao,agendadopor,empresa,telefone,celular,"
				+ "endereco,quadra,lote,email,contato,hora_inicio,hora_fim,"
				+ "data_inicio,data_fim,data_cadastro,ativo,confirmado,telefone, bairro, municipio, motivo";
	}
	
	public String getatributosI() {
		String atributos = "'"+ this.funcao+ "',";
		atributos = atributos + " "+ this.agendadopor + " ,";

		atributos = atributos + " "+ this.empresa + " ,";
		atributos = atributos + " "+ this.telefone + " ,";
		atributos = atributos + " "+ this.celular + " ,";
			atributos = atributos + " "+ this.endereco + " ,";
		atributos = atributos + " "+ this.quadra + " ,";
		atributos = atributos + " "+ this.lote + " ,";
			atributos = atributos + " "+ this.email + " ,";
		atributos = atributos + " "+ this.contato + " ,";
			atributos = atributos + " "+ this.hora_inicio + " ,";
		atributos = atributos + " "+ this.hora_fim + " ,";
		atributos = atributos + " "+ this.data_inicio + " ,";
		atributos = atributos + " "+ this.data_fim + " ,";
		atributos = atributos + " "+ this.data_cadastro + " ,";
		atributos = atributos + " "+ this.ativo + " ,";
		atributos = atributos + " "+ this.confirmado + " ,";
		atributos = atributos + " "+ this.ativo + " ,";
		atributos = atributos + " "+ this.bairro + " ,";
		atributos = atributos + " "+ this.municipio + " ,";
		atributos = atributos + " "+ this.motivo + " ,";
		return atributos;
	}

	public int getIdvisita() {
		return idvisita;
	}

	public void setIdvisita(int idvisita) {
		this.idvisita = idvisita;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public String getAgendadopor() {
		return agendadopor;
	}

	public void setAgendadopor(String agendadopor) {
		this.agendadopor = agendadopor;
	}


	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_fim() {
		return hora_fim;
	}

	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public String getOrdenacao() {
		// TODO Auto-generated method stub
		return "data_cadastro";
	}

	@Override
	public String getVariaveisPesquisarNome() {
		// TODO Auto-generated method stub
		return "agendadopor";
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
