package br.ueg.unucet.gymsys.Enum;

public enum Horas {
	

	OITO(1, "08:00"),
	OITO_MEIA(2, "08:30"), 
	NOVE(3, "09:00"),
	NOVE_MEIA(4, "09:30"), 
	DEZ(5,"10:00"),
	DEZ_MEIA(6,"10:30"),
	ONZE(7,"11:00"),
	ONZE_MEIA(8,"11:30"),
	DOZE(9, "12:00"), 
	DOZE_MEIA(10, "12:30"),
	TREZE(11, "13:00"),
	TREZE_MEIA(12, "13:30"), 
	QUATORZE(13,"14:00"),
	QUATORZE_MEIA(14,"14:30"),
	QUINZE(15,"15:00"),
	QUINZE_MEIA(16,"15:30"),
	DEZESSEIS(17, "16:00"),
	DEZESSEIS_MEIA(18, "16:30"), 
	DEZESSETE(19, "17:00"),
	DEZESSETE_MEIA(20, "17:30"),
;
	
	private Integer id;
	private String descricao;

	private Horas(Integer id, String descricao) {
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
