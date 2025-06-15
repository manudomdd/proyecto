/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class ClienteDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private Connection databaseConnection;
	private Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public ClienteDAO() {
        this.databaseConnection = conectar();
    }
    
    private Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
            return con;
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Metodo para saber si existe ya el cliente para darlo de alta.
     * @param nif
     * @return
     */
    
    public boolean existeCliente(String nif) {
    	String sql = "SELECT COUNT(*) FROM clientes WHERE nif = ?"; 
    	
    	try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
    		stmt.setString(1, nif);
    		ResultSet rs = stmt.executeQuery(); 
    		
    		if (rs.next()) {
    			int count = rs.getInt(1);
    			return count > 0;
    		}
    	} catch (SQLException ex) {
    		System.out.println("Error al comprobar la existencia del cliente: " + ex.getMessage());
    	}
    	
    	return false; 
    }
    
    /**
     * Metodo para dar de alta a un cliente.
     */
    
    public void altaCliente() {
        try {
            System.out.println("Introduzca el NIF del cliente:");
            String nif = scanner.nextLine();
            
            if (!existeCliente(nif)) {	
                System.out.print("Introduzca el nombre del cliente: ");
                String name = scanner.nextLine();

                System.out.print("Introduce los apellidos del cliente: ");
                String surname = scanner.nextLine();

                System.out.println("Introduzca la fecha de nacimiento (dd/MM/yyyy):"); 
                String fechaStr = scanner.nextLine(); 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
                LocalDate fechaNac = LocalDate.parse(fechaStr, formatter); 

                System.out.print("Introduce el sexo del cliente: ");
                String sex = scanner.nextLine();

                System.out.print("Introduce la altura del cliente (en cm): ");
                int height = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Introduzca el peso del cliente (en kg): ");
                double weight = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Introduzca el nivel de actividad diaria (1-5): ");
                int activityLevel = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Introduzca la cuota mensual: ");
                double mensualFee = scanner.nextDouble();
                scanner.nextLine();

                LocalDate fechaAlta = LocalDate.now();
                LocalDate fechaBaja = fechaAlta.plusMonths(1);

                String sqlUsuario = "INSERT INTO usuarios (nif, nombre, apellidos, fechaNac, sexo, altura, peso, actividad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt1 = databaseConnection.prepareStatement(sqlUsuario)) {
                    pstmt1.setString(1, nif);
                    pstmt1.setString(2, name);
                    pstmt1.setString(3, surname);
                    pstmt1.setDate(4, java.sql.Date.valueOf(fechaNac));
                    pstmt1.setString(5, sex);
                    pstmt1.setInt(6, height);
                    pstmt1.setDouble(7, weight);
                    pstmt1.setInt(8, activityLevel);
                    pstmt1.executeUpdate();
                }

                String sqlCliente = "INSERT INTO clientes (nif, nifEntrenador, fechaAlta, fechaBaja, cuota, activo) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt2 = databaseConnection.prepareStatement(sqlCliente)) {
                    pstmt2.setString(1, nif);
                    pstmt2.setNull(2, java.sql.Types.VARCHAR);  // nifEntrenador como NULL
                    pstmt2.setDate(3, java.sql.Date.valueOf(fechaAlta));
                    pstmt2.setDate(4, java.sql.Date.valueOf(fechaBaja));
                    pstmt2.setDouble(5, mensualFee);
                    pstmt2.setBoolean(6, true);
                    pstmt2.executeUpdate();
                }

                String sqlPago = "INSERT INTO pagos (idPago, idCliente, monto, metodoPago, concepto) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt3 = databaseConnection.prepareStatement(sqlPago)) {
                    String idPago = UUID.randomUUID().toString();

                    System.out.println("Seleccione el método de pago (Efectivo, Tarjeta, Transferencia): ");
                    String metodoPago = scanner.nextLine();

                    while (!metodoPago.equalsIgnoreCase("Efectivo") &&
                           !metodoPago.equalsIgnoreCase("Tarjeta") &&
                           !metodoPago.equalsIgnoreCase("Transferencia")) {
                        System.out.println("Método no válido. Introduzca 'Efectivo', 'Tarjeta' o 'Transferencia': ");
                        metodoPago = scanner.nextLine();
                    }

                    pstmt3.setString(1, idPago);
                    pstmt3.setString(2, nif);
                    pstmt3.setDouble(3, mensualFee);
                    pstmt3.setString(4, metodoPago);
                    pstmt3.setString(5, "Alta de cliente");
                    pstmt3.executeUpdate();
                }

                System.out.println("Cliente dado de alta correctamente.");

            } else {
                System.out.println("El cliente ya se encuentra dado de alta."); 
            }

        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error de entrada: formato no válido.");
            scanner.nextLine(); 
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Use dd/MM/yyyy.");
        }
    }
    
    /**
     * Metodo para eliminar un cliente.
     */
    
    public void eliminarCliente() {
    	try {
    		System.out.println("Introduzca el nif del cliente a dar de baja: "); 
    		String nif = scanner.nextLine(); 
    		
    		if (!existeCliente(nif)) {
    			System.out.println("Este cliente no se encuentra dado de alta."); 
    		} else {
    			String sql = "DELETE FROM clientes WHERE nif = ?"; 
    			try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
    				stmt.setString(1, nif);
    				int rowsAffected = stmt.executeUpdate(); 
    				
    				if (rowsAffected > 0) {
    					System.out.println("Cliente eliminado."); 					
    				} else {
    					System.out.println("Hubo un error dando de baja al cliente."); 
    				}			
    			}
    		}
    	} catch (InputMismatchException ex) {
    		System.out.println("Error de entrada: formato no valido.");     		
    	} catch (SQLException ex) {
    		System.out.println("Error de base de datos: " + ex.getMessage());
    	}
    }
    
    /**
     * Metodo para dar de baja a un cliente.
     */
    
    public void bajaCliente() {
        try {
            System.out.println("Introduzca el NIF del cliente a dar de baja:");
            String nif = scanner.nextLine();

            if (!existeCliente(nif)) {
                System.out.println("Este cliente no se encuentra dado de alta.");
            } else {
                String sql = "UPDATE clientes SET activo = false, fecha_baja = ? WHERE nif = ?";
                try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
                    stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                    stmt.setString(2, nif);

                    int filasAfectadas = stmt.executeUpdate();
                    if (filasAfectadas > 0) {
                        System.out.println("Cliente dado de baja correctamente (baja lógica).");
                    } else {
                        System.out.println("No se pudo dar de baja al cliente.");
                    }
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Error de entrada: formato no válido.");
        } catch (SQLException e) {
            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
        }
    }
    
    /**
     * Metodo para modificar los datos de un cliente.
     */
    
    public void modificarCliente() {
        try {
            System.out.println("Introduzca el NIF del cliente a modificar:");
            String nif = scanner.nextLine();

            if (!existeCliente(nif)) {
                System.out.println("Este cliente no se encuentra dado de alta.");
                return;
            }

            System.out.print("Nuevo nombre (dejar en blanco para no modificar): ");
            String name = scanner.nextLine();

            System.out.print("Nuevos apellidos (dejar en blanco para no modificar): ");
            String surname = scanner.nextLine();

            System.out.print("Nueva edad (-1 para no modificar): ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo sexo (dejar en blanco para no modificar): ");
            String sex = scanner.nextLine();

            System.out.print("Nueva altura (-1 para no modificar): ");
            int height = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo peso (-1 para no modificar): ");
            double weight = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Nuevo nivel de actividad diaria (1-5, -1 para no modificar): ");
            int activityLevel = scanner.nextInt();
            scanner.nextLine();

            String sql = "UPDATE usuarios SET " +
                    "nombre = COALESCE(NULLIF(?, ''), nombre), " +
                    "apellidos = COALESCE(NULLIF(?, ''), apellidos), " +
                    "edad = CASE WHEN ? = -1 THEN edad ELSE ? END, " +
                    "sexo = COALESCE(NULLIF(?, ''), sexo), " +
                    "altura = CASE WHEN ? = -1 THEN altura ELSE ? END, " +
                    "peso = CASE WHEN ? = -1 THEN peso ELSE ? END, " +
                    "actividad = CASE WHEN ? = -1 THEN actividad ELSE ? END " +
                    "WHERE nif = ?";

            try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, surname);
                stmt.setInt(3, age);
                stmt.setInt(4, age);
                stmt.setString(5, sex);
                stmt.setInt(6, height);
                stmt.setInt(7, height);
                stmt.setDouble(8, weight);
                stmt.setDouble(9, weight);
                stmt.setInt(10, activityLevel);
                stmt.setInt(11, activityLevel);
                stmt.setString(12, nif);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Datos del cliente modificados correctamente.");
                } else {
                    System.out.println("No se realizaron cambios.");
                }
            }

        } catch (InputMismatchException ex) {
            System.out.println("Error de entrada: formato no válido.");
            scanner.nextLine(); 
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }
    
    /**
     * Metodo para listar cliente en riesgo de baja. 
     */
    
    public void clientesConRiesgoDeBaja() {
        String sql = "SELECT u.nif, u.nombre, u.apellidos, c.cuota, c.fechaBaja\r\n"
        		+ "FROM clientes c\r\n"
        		+ "JOIN usuarios u ON c.nif = u.nif\r\n"
        		+ "WHERE c.activo = TRUE \r\n"
        		+ "AND c.fechaBaja <= CURRENT_DATE - INTERVAL 7 DAY;";

        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Clientes con riesgo de baja por impago (más de 7 días desde fecha de baja):");

            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                String nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                double cuota = rs.getDouble("cuota");
                Date fechaBaja = rs.getDate("fechaBaja");

                System.out.println("NIF: " + nif);
                System.out.println("Nombre: " + nombre + " " + apellidos);
                System.out.println("Cuota: " + cuota);
                System.out.println("Fecha de baja: " + fechaBaja);
                System.out.println("---------------------------------");

            }

            if (!hayResultados) {
                System.out.println("No hay clientes con riesgo de baja por impago.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta de clientes con riesgo de baja: " + e.getMessage());
        }
    }
    
    /**
     * Metodo para actualizar el estado de los clientes inactivos.
     */
    
    public void actualizarInactivos() {
    	String sql = "{ CALL actualizarClientesInactivos() }";

        try (CallableStatement stmt = databaseConnection.prepareCall(sql)) {
            stmt.execute();
            System.out.println("Clientes actualizados correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el procedimiento: " + e.getMessage());
        }
    }
    
    /**
     * Metodo para mostrar los clientes que tienen cuotas vencidas.
     */
    
    public void mostrarClientesConCuotasVencidas() {
        String sql = "SELECT * FROM clientesConCuotasVencidas";

        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;

                String nif = rs.getString("nif_cliente");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                double cuota = rs.getDouble("cuota");
                Date fechaPago = rs.getDate("ultima_fecha_pago");
                int diasVencidos = rs.getInt("dias_desde_ultimo_pago");

                System.out.println("NIF: " + nif);
                System.out.println("Nombre: " + nombre + " " + apellidos);
                System.out.println("Cuota: " + cuota);
                System.out.println("Última fecha de pago: " + fechaPago);
                System.out.println("Días desde el último pago: " + diasVencidos);
                System.out.println("----------------------------");
            }

            if (!hayResultados) {
                System.out.println("No hay clientes con cuotas vencidas.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar la vista: " + e.getMessage());
        }
    }
    
    /**
     * Metodo para listar clientes que siguen activos pero que no han pagado.
     */

    public void clientesActivosSinPagar() {
    	String sql = "SELECT c.nif, u.nombre, u.apellidos\r\n"
    			+ "FROM clientes c\r\n"
    			+ "JOIN usuarios u ON c.nif = u.nif\r\n"
    			+ "LEFT JOIN pagos p ON c.nif = p.nifCliente AND MONTH(p.fechaPago) = MONTH(CURDATE())\r\n"
    			+ "WHERE c.activo = 1 AND p.nifCliente IS NULL;\r\n"; 
    	
    	try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
    		ResultSet rs = stmt.executeQuery(); 
    		boolean exists = false; 
    		while (rs.next()) {
    			exists = true; 
    			String nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                
                System.out.println("NIF: " + nif);
                System.out.println("Nombre: " + nombre + " " + apellidos);
    		}
    		
    		if (!exists) {
    			System.out.println("Todos estan al corriente de pago."); 
    		}
    	} catch (SQLException ex) {
    		System.out.println("Error con la base de datos: " + ex.getMessage()); 
    		
    	}
    }
  

}