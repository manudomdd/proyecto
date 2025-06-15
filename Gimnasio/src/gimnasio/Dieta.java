/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.util.Objects;

public class Dieta {
	
	protected String id; 
	protected String nifCliente; 
	protected String idEntrenador; 
	protected int numCalorias;
	protected String objetivo; 
	protected int grCarbs; 
	protected int grProt; 
	protected int grGrasas;
	/**
	 * @param id
	 * @param nifCliente
	 * @param idEntrenador
	 * @param numCalorias
	 * @param objetivo
	 * @param grCarbs
	 * @param grProt
	 * @param grGrasas
	 */
	public Dieta(String id, String nifCliente, String idEntrenador, int numCalorias, String objetivo, int grCarbs,
			int grProt, int grGrasas) {
		super();
		this.id = id;
		this.nifCliente = nifCliente;
		this.idEntrenador = idEntrenador;
		this.numCalorias = numCalorias;
		this.objetivo = objetivo;
		this.grCarbs = grCarbs;
		this.grProt = grProt;
		this.grGrasas = grGrasas;
	}
	
	
	/**
	 * 
	 */
	public Dieta() {
		super();
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the nifCliente
	 */
	public String getNifCliente() {
		return nifCliente;
	}
	/**
	 * @param nifCliente the nifCliente to set
	 */
	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}
	/**
	 * @return the idEntrenador
	 */
	public String getIdEntrenador() {
		return idEntrenador;
	}
	/**
	 * @param idEntrenador the idEntrenador to set
	 */
	public void setIdEntrenador(String idEntrenador) {
		this.idEntrenador = idEntrenador;
	}
	/**
	 * @return the numCalorias
	 */
	public int getNumCalorias() {
		return numCalorias;
	}
	/**
	 * @param numCalorias the numCalorias to set
	 */
	public void setNumCalorias(int numCalorias) {
		this.numCalorias = numCalorias;
	}
	/**
	 * @return the objetivo
	 */
	public String getObjetivo() {
		return objetivo;
	}
	/**
	 * @param objetivo the objetivo to set
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	/**
	 * @return the grCarbs
	 */
	public int getGrCarbs() {
		return grCarbs;
	}
	/**
	 * @param grCarbs the grCarbs to set
	 */
	public void setGrCarbs(int grCarbs) {
		this.grCarbs = grCarbs;
	}
	/**
	 * @return the grProt
	 */
	public int getGrProt() {
		return grProt;
	}
	/**
	 * @param grProt the grProt to set
	 */
	public void setGrProt(int grProt) {
		this.grProt = grProt;
	}
	/**
	 * @return the grGrasas
	 */
	public int getGrGrasas() {
		return grGrasas;
	}
	/**
	 * @param grGrasas the grGrasas to set
	 */
	public void setGrGrasas(int grGrasas) {
		this.grGrasas = grGrasas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(grCarbs, grGrasas, grProt, id, idEntrenador, nifCliente, numCalorias, objetivo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dieta other = (Dieta) obj;
		return grCarbs == other.grCarbs && grGrasas == other.grGrasas && grProt == other.grProt
				&& Objects.equals(id, other.id) && Objects.equals(idEntrenador, other.idEntrenador)
				&& Objects.equals(nifCliente, other.nifCliente) && numCalorias == other.numCalorias
				&& Objects.equals(objetivo, other.objetivo);
	}
	@Override
	public String toString() {
		return "Dieta [id=" + id + ", nifCliente=" + nifCliente + ", idEntrenador=" + idEntrenador + ", numCalorias="
				+ numCalorias + ", objetivo=" + objetivo + ", grCarbs=" + grCarbs + ", grProt=" + grProt + ", grGrasas="
				+ grGrasas + "]";
	} 
	
	
	

}
