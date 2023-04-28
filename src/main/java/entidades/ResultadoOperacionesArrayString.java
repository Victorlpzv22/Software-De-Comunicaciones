package entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultadoOperacionesArrayString {
	private String[] resultado;

	public ResultadoOperacionesArrayString() {
	}

	public ResultadoOperacionesArrayString(String[] resultado) {
		this.resultado = resultado;
	}

	@XmlElement
	public String[] getResultado() {
		return resultado;
	}

	public void setResultado(String[] resultado) {
		this.resultado = resultado;
	}

}
