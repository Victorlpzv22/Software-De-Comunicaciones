package servicioCRUD;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;

//http://localhost:8080/p4/rest/calculadora/sumar?operando1=2&operando2=3 -> sumar GET

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import entidades.ResultadoOperaciones;
import entidades.ResultadoOperacionesArrayString;
import operaciones.OperacionesCalculadora;

@Path("/calculadora")
public class OperacionesCalculadoraREST {

	@Context
	private HttpServletRequest hsr;

	private OperacionesCalculadora getOperacionesCalculadora() {
		final String ATRIBUTOCALCULADORA = "operaciones";
		final HttpSession sesion = hsr.getSession();
		OperacionesCalculadora operaciones = (OperacionesCalculadora) sesion.getAttribute(ATRIBUTOCALCULADORA);
		if (operaciones == null) {
			operaciones = new OperacionesCalculadora();
			sesion.setAttribute(ATRIBUTOCALCULADORA, operaciones);
		}
		return operaciones;
	}

	@Path("/operaciones")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response operaciones(@QueryParam("numeroOperaciones") int numeroOperaciones) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		String[] resultado = getOperacionesCalculadora().getOperaciones(numeroOperaciones);
		respuesta = respuesta.entity(new ResultadoOperacionesArrayString(resultado));

		return respuesta.build();
	}

	@Path("/operar")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response operar(@QueryParam("operacion") int operacion, @QueryParam("operando") double operando) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);

		try {
			double resultado = getOperacionesCalculadora().operar(operacion, operando);
			respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		} catch (Exception e) {
			respuesta = Response.status(Response.Status.PRECONDITION_FAILED);
			respuesta.type(MediaType.TEXT_PLAIN);
			respuesta.entity(e.getMessage());
		}
		return respuesta.build();
	}

	@Path("/sumar")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response sumar(@QueryParam("operando1") double operando1, @QueryParam("operando2") double operando2) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		double resultado = getOperacionesCalculadora().sumar(operando1, operando2);
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));

		return respuesta.build();
	}

	@Path("/restar")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response restar(@QueryParam("operando1") double operando1, @QueryParam("operando2") double operando2) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		double resultado = getOperacionesCalculadora().restar(operando1, operando2);
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));

		return respuesta.build();
	}

	@Path("/multiplicar")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response multiplicar(@QueryParam("operando1") double operando1, @QueryParam("operando2") double operando2) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		double resultado = getOperacionesCalculadora().multiplicar(operando1, operando2);
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));

		return respuesta.build();
	}

	@Path("/dividir")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response dividir(@QueryParam("dividendo") double dividendo, @QueryParam("divisor") double divisor) {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);

		try {
			double resultado = getOperacionesCalculadora().dividir(dividendo, divisor);
			respuesta = respuesta.entity(new ResultadoOperaciones(resultado));
		} catch (Exception e) {
			respuesta = Response.status(Response.Status.PRECONDITION_FAILED);
			respuesta.type(MediaType.TEXT_PLAIN);
			respuesta.entity(e.getMessage());
		}

		return respuesta.build();
	}

	@Path("/ur")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response ur() {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		double resultado = getOperacionesCalculadora().obtenerUltimoResultado();
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));

		return respuesta.build();
	}

	@Path("/memoria")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response memoriaObtener() {
		ResponseBuilder respuesta = Response.status(Response.Status.OK);
		double resultado = getOperacionesCalculadora().memoriaObtener();
		respuesta = respuesta.entity(new ResultadoOperaciones(resultado));

		return respuesta.build();
	}

	@Path("/memoria")
	@HEAD
	@Produces(MediaType.APPLICATION_XML)
	public Response memoriaAniadir() {
		getOperacionesCalculadora().memoriaAniadir();

		return Response.status(Response.Status.OK).build();
	}

	@Path("/memoria")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public Response memoriaLimpiar() {
		getOperacionesCalculadora().memoriaLimpiar();

		return Response.status(Response.Status.OK).build();
	}

}
