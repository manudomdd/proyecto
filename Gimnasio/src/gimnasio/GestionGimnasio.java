/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;
import java.util.*;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GestionGimnasio {
	
	public static Scanner scanner = new Scanner(System.in);
	public static ClienteDAO clienteDAO = new ClienteDAO(); 
	public static ProductoDAO productoDAO = new ProductoDAO(); 
	public static RecepcionistaDAO recepcionistaDAO = new RecepcionistaDAO(); 
	public static EntrenadorDAO entrenadorDAO = new EntrenadorDAO(); 
	public static ValoracionDAO valoracionDAO = new ValoracionDAO(); 
	public static DietaDAO dietaDAO = new DietaDAO(); 
	public static RutinaDAO rutinaDAO = new RutinaDAO(); 
	public static PagosDAO pagosDAO = new PagosDAO(); 
	public static ClaseDirigidaDAO claseDirigidaDAO = new ClaseDirigidaDAO(); 
	
	/**
	 * Metodo principal.
	 * @param args
	 */
	
	public static void main (String[] args) {
		
		int option; 
		int option2; 
		int option3; 
		int option4; 
		
		do {
			System.out.println("Selecciona el area a la que quieres acceder: "); 
			System.out.println("1. Recepcionistas.");
			System.out.println("2. Entrenadores."); 
			System.out.println("3. Clientes."); 
			System.out.println("4. Salir."); 
			option = scanner.nextInt(); 
			scanner.nextLine(); 
			
			switch (option) {
			case 1 -> {
				if (recepcionistaDAO.autentificacion()) {
					do {
						System.out.println("Seleccione una opcion: "); 
						System.out.println("1. Dar de alta a un cliente."); 
						System.out.println("2. Eliminar un registro de un cliente."); 
						System.out.println("3. Dar de baja a un cliente."); 
						System.out.println("4. Modificar datos de un cliente."); 
						System.out.println("5. Listar clientes en riesgo de baja."); 
						System.out.println("6. Actualizar estado clientes inactivos."); 
						System.out.println("7. Mostrar clientes con cuotas vencidas."); 
						System.out.println("8. Añadir un producto al catalogo."); 
						System.out.println("9. Recalcular salarios entrenadores.");
						System.out.println("10. Volver al menu de seleccion de usuario."); 
						
						option2 = scanner.nextInt(); 
						switch (option2) {
						case 1 -> clienteDAO.altaCliente();
						case 2 -> clienteDAO.eliminarCliente();
						case 3 -> clienteDAO.bajaCliente();
						case 4 -> clienteDAO.modificarCliente();
						case 5 -> clienteDAO.clientesConRiesgoDeBaja();
						case 6 -> clienteDAO.actualizarInactivos();
						case 7 -> clienteDAO.mostrarClientesConCuotasVencidas();
						case 8 -> productoDAO.añadirProducto();
						case 9 -> entrenadorDAO.recalcularSalarios();
						case 10 -> System.out.println("Saliendo al menu..."); 
						default -> System.out.println("Seleccione una opcion valida."); 
					} 					
					} while (option2 != 10);	 	
				} else {
					System.out.println("Usuario o contraseña incorrectos."); 
				}
			}
			case 2 -> {
				if (entrenadorDAO.autentificacion()) {
					do {
						System.out.println("Seleccione una opcion: "); 
						System.out.println("1. Generador de dieta."); 
						System.out.println("2. Consultar la dieta de un cliente.");
						System.out.println("3. Generador de rutinas de entrenamiento.");
						System.out.println("4. Consultar listado de clientes."); 
						System.out.println("5. Ver valoraciones."); 
						System.out.println("6. Salir al menu de seleccion de usuario.");
						option3 = scanner.nextInt(); 
						scanner.nextLine(); 
						switch (option3) {
						case 1 -> {
							System.out.println("Nif del cliente: "); 
							String nifCliente = scanner.nextLine(); 
				
							try {
								dietaDAO.generarDieta(nifCliente);
							} catch (SQLException ex) {
								System.out.println("Error de la base de datos: " + ex.getMessage());
							}					
						}
						case 2 -> {
							try {
								dietaDAO.obtenerYMostrarDietaPorNif(); 
							} catch (SQLException ex) {
								System.out.println("Error en la base de datos." + ex.getMessage()); 
							}
						}
						case 3 -> {
							try {
								rutinaDAO.generarRutina();
							} catch (SQLException ex) {
								System.out.println("Hubo un error con la base de datos: " + ex.getMessage());
							}
						}
						case 5 -> valoracionDAO.valoracionesEntrenador();
						case 6 -> System.out.println("Saliendo al menu..."); 
						default -> System.out.println("Opcion no valida."); 
						}
					} while (option3 != 6); 
				} else {
					System.out.println("Usuario o contraseña incorrectos."); 
				}				
			}
			case 3 -> {
				do {
					System.out.println("1. Pagar mensualidad."); 
					System.out.println("2. Comprar producto."); 
					System.out.println("3. Puntuar un entrenador."); 
					System.out.println("4. Apuntarse a una clase dirigida."); 
					System.out.println("5. Salir al menu de seleccion de usuario.");
					option4 = scanner.nextInt(); 
					switch (option4) {
					case 1 -> pagosDAO.pagarMensualidad();
					case 2 -> productoDAO.comprarProducto();
					case 3 -> ValoracionDAO.valorarEntrenador();
					case 4 -> {
						try {
							claseDirigidaDAO.inscribirseEnClaseDirigida(); 
						} catch (SQLException ex) {
							System.out.println("Hubo un error con la base de datos: " + ex.getMessage());
						}
					}
					case 5 -> System.out.println("Saliendo al menu..."); 
			
					}
				} while (option4 != 5); 
			}
			} 
		} while (option != 4);
	}	
}





