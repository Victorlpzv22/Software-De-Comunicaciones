package rmiCalculadora;

public class CalculadoraExcepcion extends Exception {

	private static final long serialVersionUID = 1L;
	
	String descripcion;
	
	public CalculadoraExcepcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String getMessage() {
		return descripcion;
	}
}
