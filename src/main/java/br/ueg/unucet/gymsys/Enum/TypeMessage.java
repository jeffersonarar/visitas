package br.ueg.unucet.gymsys.Enum;



public enum TypeMessage {

	AVISO(1, "AVISO"), 
	CONFIRMA(2, "CONFIRMA"), 
	SUCESSO(3,"SUCESSO"),
	ERROR(4,"ERRO"),;
	
	private Integer id;
	private String descricao;

	private TypeMessage(Integer id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
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
