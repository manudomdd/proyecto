/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.time.LocalDate;
import java.util.Objects;

public class ClaseDirigida {
	
	protected String idClase; 
	protected String nombreClase; 
	protected String nifEntrenador; 
	protected int aforo; 
	protected LocalDate fecha;
	/**
	 * @param idClase
	 * @param nombreClase
	 * @param nifEntrenador
	 * @param aforo
	 * @param fecha
	 */
	public ClaseDirigida(String idClase, String nombreClase, String nifEntrenador, int aforo, LocalDate fecha) {
		super();
		this.idClase = idClase;
		this.nombreClase = nombreClase;
		this.nifEntrenador = nifEntrenador;
		this.aforo = aforo;
		this.fecha = fecha;
	}
	/**
	 * @return the idClase
	 */
	public String getIdClase() {
		return idClase;
	}
	/**
	 * @param idClase the idClase to set
	 */
	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}
	/**
	 * @return the nombreClase
	 */
	public String getNombreClase() {
		return nombreClase;
	}
	/**
	 * @param nombreClase the nombreClase to set
	 */
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
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
	 * @return the aforo
	 */
	public int getAforo() {
		return aforo;
	}
	/**
	 * @param aforo the aforo to set
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(aforo, fecha, idClase, nifEntrenador, nombreClase);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaseDirigida other = (ClaseDirigida) obj;
		return aforo == other.aforo && Objects.equals(fecha, other.fecha) && Objects.equals(idClase, other.idClase)
				&& Objects.equals(nifEntrenador, other.nifEntrenador) && Objects.equals(nombreClase, other.nombreClase);
	}
	@Override
	public String toString() {
		return "ClaseDirigida [idClase=" + idClase + ", nombreClase=" + nombreClase + ", nifEntrenador=" + nifEntrenador
				+ ", aforo=" + aforo + ", fecha=" + fecha + "]";
	} 
}
