package br.ueg.unucet.gymsys.Enum;

public enum Agendadopor {
	
	CONSULTOR(0, "Consultor"), 
	CLIENTE(1, "Cliente"), 
	SUPERVISOR(2,"Supervisor"),;

	
	private Integer id;
	private String descricao;

	

	private Agendadopor(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
