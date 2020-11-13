package ar.edu.unlam.pb2.asignacionAsientos;

import java.util.ArrayList;

public class Avion {
	
	private Integer id;
	private String Matricula;
	private ArrayList <String> listaAsientos;
	
	
	public Avion(Integer id, String matricula) {
		this.id = id;
		Matricula = matricula;
		this.listaAsientos = new ArrayList<>();
		generarAsientos();
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMatricula() {
		return Matricula;
	}
	
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	
	public ArrayList<String> getListaAsientos() {
		return listaAsientos;
	}
	
	public void setListaAsientos(ArrayList<String> listaAsientos) {
		this.listaAsientos = listaAsientos;
	}
	
	//Busca si existe un asiento en la lista de asientos del avion
	public Boolean existeUnAsiento(String asiento) {
		if(this.listaAsientos.contains(asiento)) {
			return true;
		}
		return false;
	}
	
	private void generarAsientos() {
		String numAsiento = "";
		for(int i=1; i<=30; i++) {
			numAsiento = i + "";
			this.listaAsientos.add(numAsiento);
		}
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
		Avion other = (Avion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
