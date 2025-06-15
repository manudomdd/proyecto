/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.util.Objects;

public class Valoracion {
	
	protected String id; 
	protected String nifCliente; 
	protected String idEntrenador; 
	protected int puntuacion; 
	protected String comentario;
	/**
	 * @param id
	 * @param nifCliente
	 * @param idEntrenador
	 * @param puntuacion
	 * @param comentario
	 */
	public Valoracion(String id, String nifCliente, String idEntrenador, int puntuacion, String comentario) {
		super();
		this.id = id;
		this.nifCliente = nifCliente;
		this.idEntrenador = idEntrenador;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
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
	 * @return the puntuacion
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(comentario, id, idEntrenador, nifCliente, puntuacion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valoracion other = (Valoracion) obj;
		return Objects.equals(comentario, other.comentario) && Objects.equals(id, other.id)
				&& Objects.equals(idEntrenador, other.idEntrenador) && Objects.equals(nifCliente, other.nifCliente)
				&& puntuacion == other.puntuacion;
	}
	@Override
	public String toString() {
		return "Valoracion [id=" + id + ", nifCliente=" + nifCliente + ", idEntrenador=" + idEntrenador
				+ ", puntuacion=" + puntuacion + ", comentario=" + comentario + "]";
	}
}
