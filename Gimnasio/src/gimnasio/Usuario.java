/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.util.Objects;

public abstract class Usuario {
	
	protected String nif; 
	protected String nombre; 
	protected String apellido;
	protected int fechaNac; 
	protected String sexo; 
	protected int altura;
	protected double peso; 
	protected int actividad;
	/**
	 * @param nif
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param sexo
	 * @param altura
	 * @param peso
	 * @param actividad
	 */
	public Usuario(String nif, String nombre, String apellido, int edad, String sexo, int altura, double peso,
			int actividad) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = edad;
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
		this.actividad = actividad;
	}
	/**
	 * 
	 */
	public Usuario() {
		super();
	}
	/**
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}
	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return fechaNac;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.fechaNac = edad;
	}
	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}
	/**
	 * @param altura the altura to set
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	/**
	 * @return the actividad
	 */
	public int getActividad() {
		return actividad;
	}
	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(int actividad) {
		this.actividad = actividad;
	}
	@Override
	public int hashCode() {
		return Objects.hash(actividad, altura, apellido, fechaNac, nif, nombre, peso, sexo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return actividad == other.actividad && altura == other.altura && Objects.equals(apellido, other.apellido)
				&& fechaNac == other.fechaNac && Objects.equals(nif, other.nif) && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso)
				&& Objects.equals(sexo, other.sexo);
	}
	@Override
	public String toString() {
		return "Usuario [nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + fechaNac + ", sexo="
				+ sexo + ", altura=" + altura + ", peso=" + peso + ", actividad=" + actividad + "]";
	}
}
