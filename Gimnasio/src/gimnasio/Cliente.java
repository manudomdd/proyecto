/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;
import java.time.*;
import java.util.Objects;

public class Cliente extends Usuario {
	
	protected LocalDate fechaAlta;
	protected LocalDate fechaBaja;
	protected double cuota;
	protected boolean activo;
	/**
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param sexo
	 * @param altura
	 * @param peso
	 * @param actividad
	 * @param idCliente
	 * @param fechaAlta
	 * @param fechaBaja
	 * @param cuota
	 * @param activo
	 */
	public Cliente(String nif, String nombre, String apellido, int edad, String sexo, int altura, double peso, int actividad,
			LocalDate fechaAlta, LocalDate fechaBaja, double cuota, boolean activo) {
		super(nif, nombre, apellido, edad, sexo, altura, peso, actividad);
		this.fechaAlta = fechaAlta;
		this.fechaBaja = null;
		this.cuota = cuota;
		this.activo = true;
	}

	/**
	 * @return the fechaAlta
	 */
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the fechaBaja
	 */
	public LocalDate getFechaBaja() {
		return fechaBaja;
	}
	/**
	 * @param fechaBaja the fechaBaja to set
	 */
	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	/**
	 * @return the cuota
	 */
	public double getCuota() {
		return cuota;
	}
	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(activo, cuota, fechaAlta, fechaBaja);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return activo == other.activo && Double.doubleToLongBits(cuota) == Double.doubleToLongBits(other.cuota)
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(fechaBaja, other.fechaBaja);
	}

	@Override
	public String toString() {
		return "Cliente [fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", cuota=" + cuota + ", activo="
				+ activo + ", nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac
				+ ", sexo=" + sexo + ", altura=" + altura + ", peso=" + peso + ", actividad=" + actividad + "]";
	}
}
