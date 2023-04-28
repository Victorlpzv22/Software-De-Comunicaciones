package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmiCalculadora.ICalculadoraRMI;
import rmiCalculadora.OperacionesCalculadoraRMI;

//-Djava.security.manager -Djava.security.policy="servidor.policy"

public class ServidorCalculadora {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) { System.setSecurityManager(new SecurityManager()); }
		try {
			ICalculadoraRMI operaciones = new OperacionesCalculadoraRMI();
			ICalculadoraRMI remoteStub = (ICalculadoraRMI) UnicastRemoteObject.exportObject(operaciones, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Calculadora", remoteStub);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}