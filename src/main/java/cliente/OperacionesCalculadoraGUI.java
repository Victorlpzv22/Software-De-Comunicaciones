package cliente;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

import CalculadoraGUI.ICalculadora;
import entidades.ResultadoOperaciones;
import entidades.ResultadoOperacionesArrayString;

public class OperacionesCalculadoraGUI implements ICalculadora {

	private Cookie sessionId;
	private ClientConfig cc;
	private Client cliente;

	private WebTarget target;

	public OperacionesCalculadoraGUI() {
		this.sessionId = null;
		this.cc = new ClientConfig();
		this.cliente = ClientBuilder.newClient(cc);
		this.target = cliente.target("http://localhost:8080/p4/rest/calculadora");
	}

	@Override
	public String[] getOperaciones(int numeroBotonesDisponibles) {
		Builder request = target.path("/operaciones").queryParam("numeroOperaciones", numeroBotonesDisponibles)
				.request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		ResultadoOperacionesArrayString respuesta = response.readEntity(ResultadoOperacionesArrayString.class);
		return respuesta.getResultado();
	}

	@Override
	public double operar(int operacion, double operando) throws Exception {
		Builder request = target.path("/operar").queryParam("operacion", operacion).queryParam("operando", operando)
				.request().accept(MediaType.APPLICATION_XML).accept(MediaType.TEXT_PLAIN);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		if (response.getStatusInfo() == Status.PRECONDITION_FAILED)
			throw new Exception(response.readEntity(String.class));

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();
	}

	@Override
	public double sumar(double operando1, double operando2) {
		Builder request = target.path("/sumar").queryParam("operando1", operando1).queryParam("operando2", operando2)
				.request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();
	}

	@Override
	public double restar(double operando1, double operando2) {
		Builder request = target.path("/restar").queryParam("operando1", operando1).queryParam("operando2", operando2)
				.request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();
	}

	@Override
	public double multiplicar(double operando1, double operando2) {
		Builder request = target.path("/multiplicar").queryParam("operando1", operando1)
				.queryParam("operando2", operando2).request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();

	}

	@Override
	public double dividir(double dividendo, double divisor) throws Exception {
		Builder request = target.path("/dividir").queryParam("dividendo", dividendo).queryParam("divisor", divisor)
				.request().accept(MediaType.APPLICATION_XML).accept(MediaType.TEXT_PLAIN);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		if (response.getStatusInfo() == Status.PRECONDITION_FAILED)
			throw new Exception(response.readEntity(String.class));

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();
	}

	@Override
	public double obtenerUltimoResultado() {
		Builder request = target.path("/ur").request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();
	}

	@Override
	public double memoriaObtener() {
		Builder request = target.path("/memoria").request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.get();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");

		ResultadoOperaciones respuesta = response.readEntity(ResultadoOperaciones.class);
		return respuesta.getResultado();
	}

	@Override
	public void memoriaAniadir() {
		Builder request = target.path("/memoria").request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.head();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
	}

	@Override
	public void memoriaLimpiar() {
		Builder request = target.path("/memoria").request().accept(MediaType.APPLICATION_XML);

		if (sessionId != null)
			request = request.cookie(sessionId);
		Response response = request.delete();
		if (sessionId == null)
			sessionId = response.getCookies().get("JSESSIONID");
	}
}
