package operaciones;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OperacionesCalculadora {

	private double memoria;
	private double ultimoResultado;
	final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock w = rwl.writeLock();
	private final Lock r = rwl.writeLock();

	public double sumar(double operando1, double operando2) {
		double resultado = operando1 + operando2;
		ultimoResultadoConcurrente(resultado);
		return resultado;
	}

	public double restar(double operando1, double operando2) {
		double resultado = operando1 - operando2;
		ultimoResultadoConcurrente(resultado);
		return resultado;
	}

	public double multiplicar(double operando1, double operando2) {
		double resultado = operando1 * operando2;
		ultimoResultadoConcurrente(resultado);
		return resultado;
	}

	public double dividir(double dividendo, double divisor) throws Exception {
		if (divisor == 0) {
			if (dividendo == 0)
				throw new Exception("NaN. Dividendo y Divisor igual a 0.");
			else
				throw new Exception("Infinito. Divisor igual a 0.");
		} else {
			double resultado = dividendo / divisor;
			ultimoResultadoConcurrente(resultado);
			return resultado;
		}
	}

	public String[] getOperaciones(int numeroBotonesDisponibles) {
		int num = 4;
		if (numeroBotonesDisponibles < 4) {
			num = numeroBotonesDisponibles;
		}
		String operaciones[] = new String[num];
		if (numeroBotonesDisponibles > 0) {
			operaciones[0] = "X^2";
			if (numeroBotonesDisponibles > 1) {
				operaciones[1] = "sqrt(x)";
				if (numeroBotonesDisponibles > 2) {
					operaciones[2] = "ln(x)";
					if (numeroBotonesDisponibles > 3) {
						operaciones[3] = "tg(x)";
					}
				}
			}
		}
		return operaciones;
	}

	public double operar(int numeroDeOperacion, double operando) throws Exception {
		switch (numeroDeOperacion) {
		case 0:
			double potencia = operando * operando;
			ultimoResultadoConcurrente(potencia);
			return potencia;
		case 1:
			if (operando < 0)
				throw new Exception("Resultado complejo. Raíz cuadrada de número negativo.");
			else {
				double resultado = Math.sqrt(operando);
				ultimoResultadoConcurrente(resultado);
				return resultado;
			}
		case 2:
			if (operando == 0)
				throw new Exception("Indefinido. Logaritmo neperiano de 0.");
			else if (operando < 0)
				throw new Exception("No existe. Logaritmo neperiano de número negativo.");
			else {
				double resultado = Math.log(operando);
				ultimoResultadoConcurrente(resultado);
				return resultado;
			}
		case 3:
			double cociente = operando / (Math.PI / 2);
			double resto = operando % (Math.PI / 2);
			if (cociente % 2 != 0 && resto == 0 && operando != 0)
				throw new Exception("Indefinido. Tangente de ángulo múltiplo impar de PI/2");
			else {
				double resultado = Math.tan(operando);
				ultimoResultadoConcurrente(resultado);
				return resultado;
			}
		default:
			throw new Exception("Número de operación no válido.");
		}
	}

	public double obtenerUltimoResultado() {
		double resu = 0;
		r.lock();
		resu = ultimoResultado;
		r.unlock();
		return resu;
	}

	public void memoriaLimpiar() {
		w.lock();
		memoria = 0;
		w.unlock();
	}

	private void ultimoResultadoConcurrente(double resultado) {
		w.lock();
		ultimoResultado = resultado;
		w.unlock();
	}

	private void memoriaConcurrente(double ultimoResultado) {
		w.lock();
		memoria = ultimoResultado;
		w.unlock();
	}

	public void memoriaAniadir() {
		memoriaConcurrente(ultimoResultado);
	}

	public double memoriaObtener() {
		double memo = 0;
		r.lock();
		memo = memoria;
		r.unlock();
		return memo;
	}
}