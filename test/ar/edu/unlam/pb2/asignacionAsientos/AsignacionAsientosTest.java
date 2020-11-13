package ar.edu.unlam.pb2.asignacionAsientos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class AsignacionAsientosTest {

	@Test
	public void queSePuedaCrearUnaEmpresaVacia() {
		Empresa empresa = new Empresa("Aerolineas");
		assertNotNull(empresa);
	}
	
	@Test
	public void queSePuedaRegistrarUnAvionEnLaEmpresa() {
		Empresa empresa = new Empresa("Aerolineas");
		ArrayList<String> asientosDelAvion = new ArrayList<>();
		Avion avion1 = new Avion(1, "123");
	}
	
	@Test
	public void queSePuedaBuscarUnVuelo() {
		Empresa empresa = new Empresa("Aerolineas");
		Avion avion1 = new Avion(1, "123");
		empresa.registrarAvion(avion1);
		Vuelo vuelo1 = new Vuelo(1, "San Luis", "Uruguay", avion1);
		empresa.regitrarVuelo(vuelo1);
		
		assertNotNull(empresa.buscarVuelo(vuelo1.getId()));
	}
	
	@Test
	public void queSePuedaBuscarUnPasajero() {
		Empresa empresa = new Empresa("Aerolineas");
		Pasajero manuel = new Pasajero(22556124, "Gomez");
		empresa.RegistarPasajero(manuel);
		Pasajero marta = new Pasajero(32723318, "Acevedo");
		empresa.RegistarPasajero(marta);
		
		assertEquals(2, empresa.getPasajeros().size());
		assertNotNull(empresa.buscarPasajero(manuel.getDni()));
	}
	
	@Test
	public void queSePuedaAsignarAsientosAPasajeros() {
		Empresa empresa = new Empresa("Aerolineas");
		Avion avion1 = new Avion(1, "123");
		Vuelo vuelo1 = new Vuelo(1, "San Luis", "Uruguay", avion1);
		empresa.registrarAvion(avion1);
		empresa.regitrarVuelo(vuelo1);
		Pasajero jose = new Pasajero(32723318, "Gutierrez");
		empresa.RegistarPasajero(jose);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), jose.getDni(), "4");
		Pasajero manuel = new Pasajero(55122318, "Gomez");
		empresa.RegistarPasajero(manuel);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), manuel.getDni(), "2");
		Pasajero marta = new Pasajero(17147423, "Acevedo");
		empresa.RegistarPasajero(marta);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), marta.getDni(), "5");
		
		Integer cantidadDeAsignaciones = empresa.getAsignacionesAsientos().size();
		Integer cantidadEsperada = 3;
		assertEquals(cantidadEsperada, cantidadDeAsignaciones);
		
		assertTrue(empresa.obtenerListaDeAsientoOcupadosDeUnVuelo(vuelo1.getId()).contains("4"));
		assertTrue(empresa.obtenerListaDeAsientoOcupadosDeUnVuelo(vuelo1.getId()).contains("5"));
		assertTrue(empresa.obtenerListaDeAsientoOcupadosDeUnVuelo(vuelo1.getId()).contains("2"));
	}

	@Test
	public void queSePuedaConsultarLaDisponibilidadDeUnAsiento() {
		Empresa empresa = new Empresa("Aerolineas");
		Avion avion1 = new Avion(1, "123");
		Vuelo vuelo1 = new Vuelo(1, "San Luis", "Uruguay", avion1);
		empresa.registrarAvion(avion1);
		empresa.regitrarVuelo(vuelo1);
		
		assertTrue(empresa.verificarAsientoDiponibleParaUnVuelo(vuelo1.getId(), "3"));
		
		Pasajero marta = new Pasajero(17147423, "Acevedo");
		empresa.RegistarPasajero(marta);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), marta.getDni(), "3");
		
		assertFalse(empresa.verificarAsientoDiponibleParaUnVuelo(vuelo1.getId(), "3"));
	}
	
	@Test
	public void queSePuedanConsultarLosAsientosOcupados() {
		Empresa empresa = new Empresa("Aerolineas");
		Avion avion1 = new Avion(1, "123");
		Vuelo vuelo1 = new Vuelo(1, "San Luis", "Uruguay", avion1);
		empresa.registrarAvion(avion1);
		empresa.regitrarVuelo(vuelo1);
		Pasajero jose = new Pasajero(32723318, "Gutierrez");
		empresa.RegistarPasajero(jose);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), jose.getDni(), "4");
		Pasajero manuel = new Pasajero(55122318, "Gomez");
		empresa.RegistarPasajero(manuel);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), manuel.getDni(), "8");
		Pasajero marta = new Pasajero(17147423, "Acevedo");
		empresa.RegistarPasajero(marta);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), marta.getDni(), "2");
		
		Integer cantidadAsientosOcupados = empresa.obtenerListaDeAsientoOcupadosDeUnVuelo(vuelo1.getId()).size();
		Integer cantidadEsperada = 3;
		
		assertEquals(cantidadEsperada, cantidadAsientosOcupados);
	}
	
	@Test
	public void queSePuedanConsultarLosAsientosDisponibles() {
		Empresa empresa = new Empresa("Aerolineas");
		Avion avion1 = new Avion(1, "123");
		Vuelo vuelo1 = new Vuelo(1, "San Luis", "Uruguay", avion1);
		empresa.registrarAvion(avion1);
		empresa.regitrarVuelo(vuelo1);
		Pasajero jose = new Pasajero(32723318, "Gutierrez");
		empresa.RegistarPasajero(jose);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), jose.getDni(), "4");
		Pasajero manuel = new Pasajero(55122318, "Gomez");
		empresa.RegistarPasajero(manuel);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), manuel.getDni(), "8");
		Pasajero marta = new Pasajero(17147423, "Acevedo");
		empresa.RegistarPasajero(marta);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), marta.getDni(), "2");
		
		Integer cantidadAsientosOcupados = empresa.obtenerListaDeAsientoDisponibleDeUnVuelo(vuelo1.getId()).size();
		Integer cantidadEsperada = 27;
		
		assertEquals(cantidadEsperada, cantidadAsientosOcupados);
	}
	
	@Test 
	public void queSePuedaCambiarELAsientoAUnPasajero() {
		Empresa empresa = new Empresa("Aerolineas");
		Avion avion1 = new Avion(1, "123");
		Vuelo vuelo1 = new Vuelo(1, "San Luis", "Uruguay", avion1);
		empresa.registrarAvion(avion1);
		empresa.regitrarVuelo(vuelo1);
		Pasajero manuel = new Pasajero(55122318, "Gomez");
		empresa.RegistarPasajero(manuel);
		empresa.asignarAsientoPasajeroParaUnVuelo(vuelo1.getId(), manuel.getDni(), "8");
		
		
		assertTrue(empresa.cambiarAsientoDeUnPasajeroParaUnVuelo(vuelo1.getId(), manuel.getDni(), "23"));	
		assertFalse(empresa.verificarAsientoDiponibleParaUnVuelo(vuelo1.getId(), "23"));
		assertTrue(empresa.verificarAsientoDiponibleParaUnVuelo(vuelo1.getId(), "8"));
	}
	
	
	
	
}
