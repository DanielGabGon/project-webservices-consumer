package com.dangabito.projectwebservicesconsumer.client;

import java.time.LocalDateTime;

import org.glassfish.jersey.client.ClientConfig;

import com.dangabito.projectwebservicesconsumer.dto.EmpleadoDTO;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Dangabito Clase que permite consumir el webservice de Empleados.
 */
public class EmpleadoWSClient {

	public static void main(String[] args) {
//
//		//:::::::::::::::::::::::::::::::GET:::::::::::::::::::::::::::::::::::::::::::::::::
//		Client client = ClientBuilder.newClient();
//		WebTarget webTarget = client.target("http://localhost:8080/proyect-webservices/dangabito/empleadosWS")
//				.path("consultarEmpleadoNumeroEmpleado").path("123456");
//
//		Invocation.Builder invocatioBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//		Response response = invocatioBuilder.get();
//		
//
//		if (response.getStatus() == 204) {
//			System.out.println("No se encontro el empleado con el número de empleado");
//		}
//
//		if (response.getStatus() == 200) {
//			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
//			System.out.println("Nombre Empleado:" + empleadoDTO.getNombre());
//
//			System.out.println("Fecha Creación de Empleado:" + empleadoDTO.getFechaCreacion());
//		}
		
		//::::::::::::::::::::::::::::::POST::::::::::::::::::::::::::::::::::::::::::::::::::::::
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/proyect-webservices/dangabito/empleadosWS")
				.path("guardarEmpleado");

		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setNumeroEmpleado("6789");
//		empleadoDTO.setNombre("Damas");
		empleadoDTO.setPrimerApellido("Perez");
		empleadoDTO.setSegundoApellido("Prado");
		empleadoDTO.setEdad(40);
		empleadoDTO.setFechaCreacion(LocalDateTime.now());
		
		Invocation.Builder invocatioBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocatioBuilder.post(Entity.entity(empleadoDTO, MediaType.APPLICATION_JSON));
		
		if(response.getStatus()==400) {
			String error=response.readEntity(String.class);
			System.out.println(error);
		}
		
		if(response.getStatus()==200) {
			EmpleadoDTO emp=response.readEntity(EmpleadoDTO.class);
			System.out.println(emp.getNombre());
			System.out.println(emp.getFechaCreacion());
		}
	}
}
