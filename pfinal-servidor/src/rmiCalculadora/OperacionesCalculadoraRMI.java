package rmiCalculadora;
import operaciones.OperacionesCalculadora;

public class OperacionesCalculadoraRMI extends OperacionesCalculadora implements ICalculadoraRMI {

	@Override
	public double dividir(double dividendo, double divisor) throws CalculadoraExcepcion {
		try {
			return super.dividir(dividendo, divisor);
		} catch (Exception e) {
			throw new CalculadoraExcepcion(e.getMessage());
		}
	}

}
