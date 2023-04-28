package rmiCalculadora;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadoraRMI extends Remote {
	
    public double sumar(double operando1, double operando2) throws RemoteException;

    public double restar(double operando1, double operando2) throws RemoteException;

    public double multiplicar(double operando1, double operando2) throws RemoteException;

    public double dividir(double dividendo, double divisor) throws CalculadoraExcepcion, RemoteException;

    public double elevarAlCuadrado(double operando) throws RemoteException;

    public double obtenerUltimoResultado() throws RemoteException;

    public void memoriaLimpiar() throws RemoteException;

    public void memoriaAniadir() throws RemoteException;

    public double memoriaObtener() throws RemoteException;
}
