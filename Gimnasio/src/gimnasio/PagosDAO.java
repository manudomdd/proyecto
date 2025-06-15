/**
 * @author Manuel Dominguez
 * @since 15/06/2025
 * @version 1.0
 */

package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class PagosDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/Gimnasio";
    private static final String USER = "root";
    private static final String PASSWORD = "manu";
	private Connection databaseConnection;
	private Scanner scanner = new Scanner(System.in);
    
 // Constructor
    public PagosDAO() {
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
     * Metodo para que los clientes puedan renovar la mensualidad.
     */
    
    public void pagarMensualidad() {
        String idPago = UUID.randomUUID().toString();
    	System.out.println("Introduzca su dni: "); 
    	String nif = scanner.nextLine(); 
    	System.out.println("Introduzca la cantidad: "); 
    	double cantidad = scanner.nextDouble(); 
    	scanner.nextLine(); 
    	System.out.println("Introduzca el metodo de pago (Efectivo, Tarjeta, Transferencia): "); 
    	String metodoPago = scanner.nextLine(); 
    	while (!metodoPago.equalsIgnoreCase("Efectivo") &&
                !metodoPago.equalsIgnoreCase("Tarjeta") &&
                !metodoPago.equalsIgnoreCase("Transferencia")) {
             System.out.println("Método no válido. Introduzca 'Efectivo', 'Tarjeta' o 'Transferencia': ");
             metodoPago = scanner.nextLine();
        }
    	LocalDate fechaPago = LocalDate.now(); 
    	String concepto = "Pago mensual";
    	
    	String sql = "INSERT INTO pagos VALUES (?, ?, ?, ?, ?, ?)"; 
    	
    	try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
    		stmt.setString(1, idPago);
    		stmt.setString(2, nif);
    		stmt.setDouble(3, cantidad);
    		stmt.setString(4, metodoPago);
    		stmt.setDate(5, java.sql.Date.valueOf(fechaPago));
    		stmt.setString(6, concepto); 
    		int rowsAffected = stmt.executeUpdate(); 
    		if (rowsAffected > 0) {
    			System.out.println("Pago registrado correctamente."); 
    		} else {
    			System.out.println("No se pudo registrar el pago."); 
    		}
    	} catch (SQLException ex) {
    		System.out.println("Hubo un error con la base de datos: " + ex.getMessage()); 
    	}
    }
}
