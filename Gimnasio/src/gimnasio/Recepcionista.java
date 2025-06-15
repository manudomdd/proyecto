/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.util.Objects;

public class Recepcionista extends Usuario {
	
	protected Turno turno;
	protected String usuario; 
	protected String password;
	protected double salario;
	/**
	 * @param nif
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param sexo
	 * @param altura
	 * @param peso
	 * @param actividad
	 * @param turno
	 * @param usuario
	 * @param password
	 * @param salario
	 */
	public Recepcionista(String nif, String nombre, String apellido, int edad, String sexo, int altura, double peso,
			int actividad, Turno turno, String usuario, String password, double salario) {
		super(nif, nombre, apellido, edad, sexo, altura, peso, actividad);
		this.turno = turno;
		this.usuario = usuario;
		this.password = password;
		this.salario = salario;
	}
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
	public Recepcionista(String nif, String nombre, String apellido, int edad, String sexo, int altura, double peso,
			int actividad) {
		super(nif, nombre, apellido, edad, sexo, altura, peso, actividad);
	}
	/**
	 * 
	 */
	public Recepcionista() {
		super();
	}
	/**
	 * @return the turno
	 */
	public Turno getTurno() {
		return turno;
	}
	/**
	 * @param turno the turno to set
	 */
	public void setTurno(Turno turno) {
		this.turno = turno;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, salario, turno, usuario);
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
		Recepcionista other = (Recepcionista) obj;
		return Objects.equals(password, other.password)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario) && turno == other.turno
				&& Objects.equals(usuario, other.usuario);
	}
	@Override
	public String toString() {
		return "Recepcionista [turno=" + turno + ", usuario=" + usuario + ", password=" + password + ", salario="
				+ salario + ", nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac
				+ ", sexo=" + sexo + ", altura=" + altura + ", peso=" + peso + ", actividad=" + actividad + "]";
	} 
}
