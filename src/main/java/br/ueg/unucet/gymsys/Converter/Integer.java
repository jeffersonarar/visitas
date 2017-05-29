package br.ueg.unucet.gymsys.Converter;


public class Integer implements Converter {

	public Object parserValue(java.lang.String value) {
		return java.lang.Integer.valueOf(value);
	}

	public Object parserValueScreenValue(java.lang.String value) {
		return this.parserValue(value);
	}
}