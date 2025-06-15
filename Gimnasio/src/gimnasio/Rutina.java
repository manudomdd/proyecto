/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.util.Objects;

public class Rutina {
	
	protected int idRutina; 
	protected String nifEntrenador; 
	protected String nifCliente; 
	protected TipoRutina tipoRutina;
	/**
	 * @param idRutina
	 * @param nifEntrenador
	 * @param nifCliente
	 * @param tipoRutina
	 */
	public Rutina(int idRutina, String nifEntrenador, String nifCliente, TipoRutina tipoRutina) {
		super();
		this.idRutina = idRutina;
		this.nifEntrenador = nifEntrenador;
		this.nifCliente = nifCliente;
		this.tipoRutina = tipoRutina;
	}
	/**
	 * @return the idRutina
	 */
	public int getIdRutina() {
		return idRutina;
	}
	/**
	 * @param idRutina the idRutina to set
	 */
	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}
	/**
	 * @return the nifEntrenador
	 */
	public String getNifEntrenador() {
		return nifEntrenador;
	}
	/**
	 * @param nifEntrenador the nifEntrenador to set
	 */
	public void setNifEntrenador(String nifEntrenador) {
		this.nifEntrenador = nifEntrenador;
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
	 * @return the tipoRutina
	 */
	public TipoRutina getTipoRutina() {
		return tipoRutina;
	}
	/**
	 * @param tipoRutina the tipoRutina to set
	 */
	public void setTipoRutina(TipoRutina tipoRutina) {
		this.tipoRutina = tipoRutina;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRutina, nifCliente, nifEntrenador, tipoRutina);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rutina other = (Rutina) obj;
		return idRutina == other.idRutina && Objects.equals(nifCliente, other.nifCliente)
				&& Objects.equals(nifEntrenador, other.nifEntrenador) && tipoRutina == other.tipoRutina;
	}
	@Override
	public String toString() {
		return "Rutina [idRutina=" + idRutina + ", nifEntrenador=" + nifEntrenador + ", nifCliente=" + nifCliente
				+ ", tipoRutina=" + tipoRutina + "]";
	} 
}
