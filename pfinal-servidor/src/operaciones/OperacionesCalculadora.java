package operaciones;

public class OperacionesCalculadora {

	private double memoria;
	private double ultimoResultado;

	public double sumar(double operando1, double operando2) {
		return ultimoResultado = operando1 + operando2;
	}

	public double restar(double operando1, double operando2) {
		return ultimoResultado = operando1 - operando2;
	}

	public double multiplicar(double operando1, double operando2) {
		return ultimoResultado = operando1 * operando2;
	}

	public double dividir(double dividendo, double divisor) throws Exception {
		return ultimoResultado = dividendo / divisor;
	}

	public double elevarAlCuadrado(double operando) {
		return ultimoResultado = operando * operando;
	}
	
	public double obtenerUltimoResultado() {
		return ultimoResultado;
	}

	public void memoriaLimpiar() {
		memoria = 0;
	}

	public void memoriaAniadir() {
		memoria = ultimoResultado;
	}

	public double memoriaObtener() {
		return memoria;
	}

}