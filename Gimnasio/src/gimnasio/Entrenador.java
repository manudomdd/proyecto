/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;
import java.util.*;
import java.time.*;

public class Entrenador extends Usuario {
	
	protected String nifEntrenador; 
	protected TipoEntrenador tipo; 
	protected LocalDate fechaAlta;
	protected LocalDate fechaBaja; 
	protected double salario;
	protected boolean activo;
	protected String usuario; 
	protected String contraseña;
	/**
	 * @param nif
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param sexo
	 * @param altura
	 * @param peso
	 * @param actividad
	 * @param nifEntrenador
	 * @param tipo
	 * @param fechaAlta
	 * @param fechaBaja
	 * @param salario
	 * @param activo
	 * @param usuario
	 * @param contraseña
	 */
	public Entrenador(String nif, String nombre, String apellido, int edad, String sexo, int altura, double peso,
			int actividad, String nifEntrenador, TipoEntrenador tipo, LocalDate fechaAlta, LocalDate fechaBaja,
			double salario, boolean activo, String usuario, String contraseña) {
		super(nif, nombre, apellido, edad, sexo, altura, peso, actividad);
		this.nifEntrenador = nifEntrenador;
		this.tipo = tipo;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.salario = salario;
		this.activo = activo;
		this.usuario = usuario;
		this.contraseña = contraseña;
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
	 * @return the tipo
	 */
	public TipoEntrenador getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoEntrenador tipo) {
		this.tipo = tipo;
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
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}
	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
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
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}
	/**
	 * @param contraseña the contraseña to set
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(activo, contraseña, fechaAlta, fechaBaja, nifEntrenador, salario, tipo, usuario);
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
		Entrenador other = (Entrenador) obj;
		return activo == other.activo && Objects.equals(contraseña, other.contraseña)
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(fechaBaja, other.fechaBaja)
				&& Objects.equals(nifEntrenador, other.nifEntrenador)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario) && tipo == other.tipo
				&& Objects.equals(usuario, other.usuario);
	}
	@Override
	public String toString() {
		return "Entrenador [nifEntrenador=" + nifEntrenador + ", tipo=" + tipo + ", fechaAlta=" + fechaAlta
				+ ", fechaBaja=" + fechaBaja + ", salario=" + salario + ", activo=" + activo + ", usuario=" + usuario
				+ ", contraseña=" + contraseña + ", nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNac=" + fechaNac + ", sexo=" + sexo + ", altura=" + altura + ", peso=" + peso + ", actividad="
				+ actividad + "]";
	}
}
