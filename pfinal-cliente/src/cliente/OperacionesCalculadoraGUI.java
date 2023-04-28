package cliente;

import java.rmi.RemoteException;

import CalculadoraGUI.ICalculadora;
import rmiCalculadora.CalculadoraExcepcion;
import rmiCalculadora.ICalculadoraRMI;

public class OperacionesCalculadoraGUI implements ICalculadora {

	final private ICalculadoraRMI stub;
	
	public OperacionesCalculadoraGUI(ICalculadoraRMI stub) {
		this.stub = stub;
	}

	@Override
	public double sumar(double operando1, double operando2) {
		try {
			return stub.sumar(operando1, operando2);
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double restar(double operando1, double operando2) {
		try {
			return stub.restar(operando1, operando2);
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double multiplicar(double operando1, double operando2) {
		try {
			return stub.multiplicar(operando1, operando2);
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double dividir(double dividendo, double divisor) throws CalculadoraExcepcion, RemoteException {
		return stub.dividir(dividendo, divisor);
	}

	@Override
	public double elevarAlCuadrado(double operando){
		try {
			return stub.elevarAlCuadrado(operando);
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public double obtenerUltimoResultado() {
		try {
			return stub.obtenerUltimoResultado();
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void memoriaLimpiar() {
		try {
			stub.memoriaLimpiar();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void memoriaAniadir() {
		try {
			stub.memoriaAniadir();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double memoriaObtener() {
		try {
			return stub.memoriaObtener();
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
