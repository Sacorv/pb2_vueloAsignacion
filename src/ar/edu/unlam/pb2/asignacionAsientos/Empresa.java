package ar.edu.unlam.pb2.asignacionAsientos;

import java.util.HashSet;

public class Empresa {

	private String nombre;
	private HashSet<Vuelo> vuelos;
	private HashSet<Pasajero> Pasajeros;
	private HashSet<AsignacionAsiento> asignacionesAsientos;
	private HashSet<Avion> aviones;

	public Empresa(String nombre) {

		this.nombre = nombre;
		this.vuelos = new HashSet<Vuelo>();
		this.Pasajeros = new HashSet<Pasajero>();
		this.asignacionesAsientos = new HashSet<AsignacionAsiento>(); 
		this.aviones=new HashSet<>();
	}

	public Boolean registrarAvion(Avion avion) {
		return this.aviones.add(avion);
	}

	public Boolean regitrarVuelo(Vuelo vuelo) {
		return this.vuelos.add(vuelo);
	}

	public Boolean RegistarPasajero(Pasajero pasajero) {
		return this.Pasajeros.add(pasajero);
	}
	
	
	//Busca un vuelo, en la lista de vuelos de la empresa, por ID
	public Vuelo buscarVuelo(Integer idVuelo) {
		Vuelo vueloBuscado = null;
		
		for(Vuelo vuelo : this.vuelos) {
			if(vuelo.getId().equals(idVuelo)) {
				vueloBuscado = vuelo;
			}
		}
		return vueloBuscado;
	}

	//Busca un pasajero, en la lista de pasajeros registrados en la empresa, por DNI
	public Pasajero buscarPasajero(Integer dni) {
		Pasajero pasajeroBuscado = null;
		for(Pasajero pasajero : this.Pasajeros) {
			if(pasajero.getDni().equals(dni)) {
				pasajeroBuscado = pasajero;
			}
		}
		return pasajeroBuscado;
	}
	
	//Busca si un avion existe en la empresa y lo devuelve
	public Avion buscarAvion(Integer idAvion) {
		Avion avionBuscado = null;
		for(Avion avion : this.aviones) {
			if(avion.getId().equals(idAvion)) {
				avionBuscado = avion;
			}
		}
		return avionBuscado;
	}
	
	//Busca si existe la asignacion de un asiento a traves del id de Vuelo
	public AsignacionAsiento buscarUnaAsignacionDeAsiento(Integer idVuelo) {
		for(AsignacionAsiento asignacion : this.asignacionesAsientos) {
			if(asignacion.getVuelo().getId().equals(idVuelo)) {
				return asignacion;
			}
		}
		return null;
	}
	
	
	
//	public Boolean asignarPasajeroAUnVuelo(Integer idVuelo, Integer dni) {
//		Vuelo vueloBuscado = buscarVuelo(idVuelo);
//		Pasajero pasajeroBuscado = buscarPasajero(dni);
//		
//		if(vueloBuscado!=null && pasajeroBuscado!=null) {
//			
//		}
//		
//		return null;
//	}
	
	
	
	public Boolean verificarSiExisteUnAsientoEnUnAvion(Integer idAvion, String asiento) {
		Boolean existeAsiento = false;
		
		Avion avionBuscado = buscarAvion(idAvion);
		if(avionBuscado!=null) {
			existeAsiento = avionBuscado.existeUnAsiento(asiento);
		}
		return existeAsiento;
	}

	
	
	public Boolean verificarAsientoDiponibleParaUnVuelo(Integer idVuelo, String asiento) {
		
		Vuelo vueloBuscado = buscarVuelo(idVuelo);
		
		if(vueloBuscado!=null) {
			for(AsignacionAsiento asignacion : this.asignacionesAsientos) {
				if(asignacion.getVuelo().getId().equals(idVuelo)) { //VER ESTA PARTE, poruqe si la asignacion existe seria que el asiento por ende esta ocupadoo??????
					if(asignacion.getAsciento().equals(asiento)) {
						return false;
					}
				}
			}	
		}
		return true;
	}
	
	
	//Registra una asignacion de asiento. Es PRIVADO ya que no puede realizarse la asignacion directamente,
	//sino que se debe invocar el metodo >>>>>> asignarAsientoPasajeroParaUnVuelo(....) donde se verifica antes de 
	//asignar: si existe el vuelo, si el asiento no ha sido asignado a otra persona previamente y si el pasajero 
	//al que se quiere asignar el asiento se encuentra registrado. 
	private void realizarUnaAsignacion(Integer id, Vuelo vuelo, Pasajero pasajero, String asiento) {
		AsignacionAsiento asignacion = new AsignacionAsiento(id, vuelo, pasajero, asiento);
			this.asignacionesAsientos.add(asignacion);
	}
	
	
	//VERIFICAR!!!!!!!!!!!!!!!
	public Boolean asignarAsientoPasajeroParaUnVuelo(Integer idVuelo, Integer dni, String numeroAsiento) {
		Boolean asignacionExitosa = false;
		
		Pasajero pasajeroBuscado = buscarPasajero(dni);
		Vuelo vueloBuscado = buscarVuelo(idVuelo);
		Integer idAsignacion = 1; 
		
		if(pasajeroBuscado!=null) {
			if(verificarAsientoDiponibleParaUnVuelo(idVuelo, numeroAsiento)) {
				idAsignacion++;
				this.realizarUnaAsignacion(idAsignacion, vueloBuscado, pasajeroBuscado, numeroAsiento);
				
				asignacionExitosa = true;
			}
		}
		return asignacionExitosa;
	}

	
	public HashSet<String> obtenerListaDeAsientoDeUnAvion(Integer idAvion) {
		HashSet<String> listaDeAsientos = new HashSet<>();
		
		Avion avionBuscado = buscarAvion(idAvion);
		if(avionBuscado!=null) {
			listaDeAsientos.addAll(avionBuscado.getListaAsientos());
		}
		return listaDeAsientos;
	}

	
	public HashSet<String> obtenerListaDeAsientoOcupadosDeUnVuelo(Integer IdVuelo) {
		Vuelo vueloBuscado = buscarVuelo(IdVuelo);
		
		HashSet<String> listaAsientos = new HashSet<>();
		listaAsientos = obtenerListaDeAsientoDeUnAvion(vueloBuscado.getAvion().getId());
		
		HashSet<String> listaDeAsientosOcupados = new HashSet<>();
		
		for (String asiento : listaAsientos) {
			for (AsignacionAsiento asignacion: this.asignacionesAsientos) {
				if(asignacion.getVuelo().equals(vueloBuscado)) {
					if(asiento.equals(asignacion.getAsciento())) {		
						listaDeAsientosOcupados.add(asiento);
					}
				}
			}
		}
		return listaDeAsientosOcupados;
	}
	
	
	public HashSet<String> obtenerListaDeAsientoDisponibleDeUnVuelo(Integer IdVuelo) {
		Vuelo vueloBuscado = buscarVuelo(IdVuelo);
		
		HashSet<String> listaAsientos = new HashSet<>();
		listaAsientos = obtenerListaDeAsientoDeUnAvion(vueloBuscado.getAvion().getId());
		
		HashSet<String> listaAsientosOcupados = new HashSet<>();
		listaAsientosOcupados = obtenerListaDeAsientoOcupadosDeUnVuelo(IdVuelo);
		
		listaAsientos.removeAll(listaAsientosOcupados);
		
		return  listaAsientos;
	}
	
	
	
	public Boolean cambiarAsientoDeUnPasajeroParaUnVuelo(Integer idVuelo, Integer dni, String nuevoAsiento) {
		Boolean cambioDeAsientoExitoso = false;
		
		Vuelo vueloBuscado = buscarVuelo(idVuelo);
		Pasajero pasajeroBuscado = buscarPasajero(dni);
		AsignacionAsiento asignacionBuscada = this.buscarUnaAsignacionDeAsiento(idVuelo);
		
		if(vueloBuscado!=null) {
			if(pasajeroBuscado!=null) {
				if(asignacionBuscada!=null) {
					asignacionBuscada.setAsciento(nuevoAsiento);
					cambioDeAsientoExitoso = true;
				}
			}
		}
		return cambioDeAsientoExitoso;
	}

	
	
}
