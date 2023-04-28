package cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import CalculadoraGUI.CalculadoraGUI;
import CalculadoraGUI.ICalculadora;
import rmiCalculadora.ICalculadoraRMI;

//-Djava.security.manager -Djava.security.policy="cliente.policy"

public class ClienteCalculadora {

	public static void main(String[] args) {
		if(System.getSecurityManager() == null) {System.setSecurityManager(new SecurityManager());}
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ICalculadoraRMI stubCliente = (ICalculadoraRMI) registry.lookup("Calculadora");
			ICalculadora operaciones = new OperacionesCalculadoraGUI(stubCliente);
			new CalculadoraGUI(operaciones);
		} catch(RemoteException | NotBoundException e) {
			System.err.println(e.getMessage());
		}
	}
}