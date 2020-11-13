package ar.edu.unlam.pb2.asignacionAsientos;

public class AsignacionAsiento {
	
	private Integer id; 
	private Vuelo vuelo;
	private Pasajero pasajero;
	private String asiento;
	
	
	public AsignacionAsiento(Integer id, Vuelo vuelo, Pasajero pasajero, String asiento) {
		this.id = id;
		this.vuelo = vuelo;
		this.pasajero = pasajero;
		this.asiento = asiento;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsignacionAsiento other = (AsignacionAsiento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Vuelo getVuelo() {
		return vuelo;
	}
	
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	
	public Pasajero getPasajero() {
		return pasajero;
	}
	
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	
	public String getAsciento() {
		return asiento;
	}
	
	public void setAsciento(String asciento) {
		this.asiento = asciento;
	}
	
	
	
	
	
	
}
